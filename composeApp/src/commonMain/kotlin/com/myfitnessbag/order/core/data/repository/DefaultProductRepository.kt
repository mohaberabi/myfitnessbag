package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.data.source.remote.KtorProductsRemoteDataSource
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.domain.repository.ProductRepository
import com.myfitnessbag.order.core.domain.source.remote.ProductRemoteDataSource
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel

class DefaultProductRepository(
    private val productsRemoteDataSource: ProductRemoteDataSource,
) : ProductRepository {
    override suspend fun getProducts(
        page: Int,
        perPage: Int,
        categoryId: String
    ): AppResult<List<ProductListingModel>, ErrorModel> {
        return AppResult.handle {
            productsRemoteDataSource.getProducts(
                page = page,
                perPage = perPage,
                categoryId = categoryId
            )
        }
    }

    override suspend fun searchProducts(
        query: String,
    ): AppResult<List<ProductListingModel>, ErrorModel> {
        return AppResult.handle {
            productsRemoteDataSource.searchProducts(
                query = query,
            )
        }
    }
}