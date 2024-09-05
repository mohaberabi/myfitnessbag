package com.myfitnessbag.order.core.data.source.remote

import com.myfitnessbag.order.core.data.source.remote.dto.ProductListingDto
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toProductModel
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.domain.source.remote.ProductRemoteDataSource
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.constants.CommonParams
import com.myfitnessbag.order.core.util.constants.EndPoints
import io.ktor.client.HttpClient
import kotlinx.coroutines.withContext


class KtorProductsRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val httpClient: HttpClient,
) : ProductRemoteDataSource {
    override suspend fun getProducts(
        page: Int,
        perPage: Int,
        categoryId: String
    ): List<ProductListingModel> {
        return withContext(dispatchers.io) {
            httpClient.get<List<ProductListingDto>>(
                route = EndPoints.PRODUCTS,
                queryParams = mapOf(
                    CommonParams.CATEGORY to categoryId,
                    CommonParams.PER_PAGE to perPage,
                    CommonParams.PAGE to page,
                )
            ).map { it.toProductModel() }
        }

    }

    override suspend fun searchProducts(
        query: String,
    ): List<ProductListingModel> {
        return withContext(dispatchers.io) {
            httpClient.get<List<ProductListingDto>>(
                route = EndPoints.PRODUCTS,
                queryParams = mapOf(
                    CommonParams.SEARCH to query,
                )
            ).map { it.toProductModel() }
        }
    }

}