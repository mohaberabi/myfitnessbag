package com.myfitnessbag.order.core.data.source.remote.dto.mapper

import com.myfitnessbag.order.core.data.source.remote.dto.CategoryDto
import com.myfitnessbag.order.core.domain.model.CategoryModel


fun CategoryDto.toCategoryModel() = CategoryModel(
    name = name,
    id = id,
    image = image?.src,
    display = display,
    parent = parent,
    menuOrder = menuOrder,
)