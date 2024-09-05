package com.myfitnessbag.order.features.product_details.domain.source.remote

import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel


interface ProductDetailRemoteDataSource {
    suspend fun getProductDetail(id: Int): ProductDetailModel
    suspend fun getRelatedProducts(ids: List<Int>): List<ProductListingModel>
}