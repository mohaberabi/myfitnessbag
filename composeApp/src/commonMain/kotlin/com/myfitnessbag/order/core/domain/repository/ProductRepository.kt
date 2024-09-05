package com.myfitnessbag.order.core.domain.repository

import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel


interface ProductRepository {
    suspend fun getProducts(
        page: Int = 1,
        perPage: Int = 10,
        categoryId: String,
    ): AppResult<List<ProductListingModel>, ErrorModel>

    suspend fun searchProducts(
        query: String,
    ): AppResult<List<ProductListingModel>, ErrorModel>
}