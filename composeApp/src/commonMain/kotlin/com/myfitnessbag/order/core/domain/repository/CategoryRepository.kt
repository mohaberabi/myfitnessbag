package com.myfitnessbag.order.core.domain.repository

import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.domain.model.CategoryModel

interface CategoryRepository {
    suspend fun getAllCategories(
        page: Int = 1,
        perPage: Int = 100,
    ): AppResult<List<CategoryModel>, ErrorModel>
}