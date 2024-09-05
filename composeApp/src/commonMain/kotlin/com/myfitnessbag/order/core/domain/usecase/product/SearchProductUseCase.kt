package com.myfitnessbag.order.core.domain.usecase.product

import com.myfitnessbag.order.core.domain.repository.ProductRepository

class SearchProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        query: String
    ) = productRepository.searchProducts(
        query = query
    )
}