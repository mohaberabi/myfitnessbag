package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.domain.repository.TokenExtractorStrategy
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.AppSupervisorScope
import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.model.UserModel
import com.myfitnessbag.order.core.domain.repository.AuthRepository
import com.myfitnessbag.order.core.domain.source.local.AuthLocalDataSource
import com.myfitnessbag.order.core.domain.source.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DefaultAuthRepository(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val tokenExtractor: TokenExtractorStrategy,
    private val appScope: AppSupervisorScope,
) : AuthRepository {
    override suspend fun login(
        request: LoginRequest,
    ): EmptyDataResult<ErrorModel> {
        return AppResult.handle {
            val response = authRemoteDataSource.login(request)
            val id = tokenExtractor.extract(response.token)
            getAndSaveUser(id!!.toInt())
        }

    }

    override suspend fun isLoggedIn(): Boolean {

        val user = authLocalDataSource.getUser().first()
        return user != null
    }

    override fun getUser(): Flow<UserModel?> = authLocalDataSource.getUser()

    override suspend fun register(
        request: RegisterRequest,
    ): EmptyDataResult<ErrorModel> {
        return AppResult.handle {
            val response = authRemoteDataSource.register(request)
            getAndSaveUser(response.id)
        }
    }


    private suspend fun getAndSaveUser(
        id: Int
    ) {
        val user = authRemoteDataSource.getUserData(id)
        appScope.superVisor.launch { authLocalDataSource.saveUser(user) }.join()
    }
}