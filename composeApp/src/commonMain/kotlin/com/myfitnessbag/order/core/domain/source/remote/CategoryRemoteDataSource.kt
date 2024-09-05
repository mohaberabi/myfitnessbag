package com.myfitnessbag.order.core.domain.source.remote

import com.myfitnessbag.order.core.domain.model.CategoryModel

interface CategoryRemoteDataSource {
    suspend fun getAllCategories(
        perPage: Int = 30,
        page: Int = 1,
    ): List<CategoryModel>
}