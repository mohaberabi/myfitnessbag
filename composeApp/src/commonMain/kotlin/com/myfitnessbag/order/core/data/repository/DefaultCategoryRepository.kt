package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.domain.repository.CategoryRepository
import com.myfitnessbag.order.core.domain.source.remote.CategoryRemoteDataSource

class DefaultCategoryRepository(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
) : CategoryRepository {
    override suspend fun getAllCategories(
        page: Int,
        perPage: Int,
    ): AppResult<List<CategoryModel>, ErrorModel> = AppResult.handle {
        categoryRemoteDataSource.getAllCategories(
            page = page,
            perPage = perPage,
        )
    }
}