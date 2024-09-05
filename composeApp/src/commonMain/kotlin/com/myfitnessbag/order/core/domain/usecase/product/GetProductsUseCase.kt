package com.myfitnessbag.order.core.domain.usecase.product

import com.myfitnessbag.order.core.domain.repository.ProductRepository

class GetProductsUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 10,
        categoryId: String,
    ) = productRepository.getProducts(
        page = page,
        perPage = perPage,
        categoryId = categoryId
    )
}