package com.myfitnessbag.order.core.domain.usecase.localization

import com.myfitnessbag.order.core.domain.repository.AppPrefsRepository

class ChangeLocalUseCase(
    private val appPrefsRepository: AppPrefsRepository,
) {
    operator fun invoke(
    ) = appPrefsRepository.changeLocal(
    )
}