package com.myfitnessbag.order.core.data.source.remote

import com.myfitnessbag.order.core.util.constants.EndPoints
import com.myfitnessbag.order.core.data.source.remote.dto.CategoryDtoResponse
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toCategoryModel
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.domain.source.remote.CategoryRemoteDataSource
import com.myfitnessbag.order.core.util.constants.CommonParams
import io.ktor.client.HttpClient


class KtorCategoryRemoteDataSource(
    private val httpClient: HttpClient,
) : CategoryRemoteDataSource {
    override suspend fun getAllCategories(
        perPage: Int,
        page: Int,
    ): List<CategoryModel> {
        val response = httpClient.get<CategoryDtoResponse>(
            route = EndPoints.Categories,
            queryParams = mapOf(
                CommonParams.PER_PAGE to perPage,
                CommonParams.PAGE to page,
            )
        )
        return response.map { it.toCategoryModel() }

    }


}