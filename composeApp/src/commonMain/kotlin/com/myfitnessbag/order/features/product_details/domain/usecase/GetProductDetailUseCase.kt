package com.myfitnessbag.order.features.product_details.domain.usecase

import com.myfitnessbag.order.features.product_details.domain.repository.ProductDetailRepository

class GetProductDetailUseCase(
    private val productDetailRepository: ProductDetailRepository,
) {


    suspend operator fun invoke(
        id: Int,
    ) = productDetailRepository.getProductDetails(id)

}