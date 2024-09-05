package com.myfitnessbag.order.features.product_details.data.repository

import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel
import com.myfitnessbag.order.features.product_details.domain.repository.ProductDetailRepository
import com.myfitnessbag.order.features.product_details.domain.source.remote.ProductDetailRemoteDataSource

class DefaultProductDetailRepository(
    private val productDetailRemoteDataSource: ProductDetailRemoteDataSource,
) : ProductDetailRepository {
    override suspend fun getProductDetails(
        id: Int,
    ): AppResult<ProductDetailModel, ErrorModel> {
        return AppResult.handle { productDetailRemoteDataSource.getProductDetail(id) }
    }

    override suspend fun getRelatedProducts(
        ids: List<Int>,
    ): AppResult<List<ProductListingModel>, ErrorModel> {
        return AppResult.handle { productDetailRemoteDataSource.getRelatedProducts(ids) }
    }
}