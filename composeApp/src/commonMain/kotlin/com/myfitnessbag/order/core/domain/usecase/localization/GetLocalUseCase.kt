package com.myfitnessbag.order.core.domain.usecase.localization

import com.myfitnessbag.order.core.domain.repository.AppPrefsRepository
import kotlinx.coroutines.flow.Flow

class GetLocalUseCase(
    private val appPrefsRepository: AppPrefsRepository,
) {


    operator fun invoke(): String = appPrefsRepository.getLocal()
}