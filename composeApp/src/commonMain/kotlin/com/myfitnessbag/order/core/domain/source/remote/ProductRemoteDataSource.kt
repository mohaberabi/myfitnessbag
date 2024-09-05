package com.myfitnessbag.order.core.domain.source.remote

import com.myfitnessbag.order.core.domain.model.ProductListingModel


interface ProductRemoteDataSource {
    suspend fun getProducts(
        page: Int = 1,
        perPage: Int = 10,
        categoryId: String,
    ): List<ProductListingModel>

    suspend fun searchProducts(
        query: String,
    ): List<ProductListingModel>
}