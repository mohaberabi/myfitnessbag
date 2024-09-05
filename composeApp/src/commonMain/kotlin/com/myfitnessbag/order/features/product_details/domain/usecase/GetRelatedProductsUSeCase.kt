package com.myfitnessbag.order.features.product_details.domain.usecase

import com.myfitnessbag.order.features.product_details.domain.repository.ProductDetailRepository

class GetRelatedProductsUSeCase(
    private val productDetailRepository: ProductDetailRepository,
) {
    suspend operator fun invoke(
        ids: List<Int>
    ) = productDetailRepository.getRelatedProducts(ids)
}