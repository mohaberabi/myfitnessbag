package com.myfitnessbag.order.features.product_details.data.source.remote

import com.myfitnessbag.order.core.data.source.remote.dto.ProductListingDto
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toProductModel
import com.myfitnessbag.order.core.data.source.remote.get
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.constants.EndPoints
import com.myfitnessbag.order.features.product_details.data.source.remote.dto.ProductDetailDto
import com.myfitnessbag.order.features.product_details.data.source.remote.dto.mappers.toProductDetailModel
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel
import com.myfitnessbag.order.features.product_details.domain.source.remote.ProductDetailRemoteDataSource
import io.ktor.client.HttpClient
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class KtorProductDetailRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val httpClient: HttpClient,
) : ProductDetailRemoteDataSource {
    override suspend fun getProductDetail(
        id: Int,
    ): ProductDetailModel {
        return withContext(dispatchers.io) {
            httpClient.get<ProductDetailDto>("${EndPoints.PRODUCTS}/${id}").toProductDetailModel()
        }
    }

    override suspend fun getRelatedProducts(
        ids: List<Int>,
    ): List<ProductListingModel> {
        return withContext(dispatchers.io) {
            ids.map { id ->
                async {
                    runCatching {
                        httpClient.get<ProductListingDto>("${EndPoints.PRODUCTS}/$id")
                            .toProductModel()
                    }.getOrNull()
                }
            }.awaitAll().filterNotNull()
        }
    }
}