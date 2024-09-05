package com.myfitnessbag.order.features.product_details.domain.repository

import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel

interface ProductDetailRepository {
    suspend fun getProductDetails(
        id: Int,
    ): AppResult<ProductDetailModel, ErrorModel>

    suspend fun getRelatedProducts(
        ids: List<Int>,
    ): AppResult<List<ProductListingModel>, ErrorModel>
}