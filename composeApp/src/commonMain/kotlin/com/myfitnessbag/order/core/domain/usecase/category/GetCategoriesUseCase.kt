package com.myfitnessbag.order.core.domain.usecase.category

import com.myfitnessbag.order.core.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoriesRepository: CategoryRepository,
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 30,
    ) = categoriesRepository.getAllCategories(
        page = page,
        perPage = perPage,
    )
}