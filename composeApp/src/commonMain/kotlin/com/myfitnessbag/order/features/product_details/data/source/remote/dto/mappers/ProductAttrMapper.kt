package com.myfitnessbag.order.features.product_details.data.source.remote.dto.mappers

import com.myfitnessbag.order.core.data.source.remote.dto.ProductListingDto
import com.myfitnessbag.order.features.product_details.data.source.remote.dto.ProductAttrDto
import com.myfitnessbag.order.features.product_details.domain.model.ProductAttrModel


fun ProductAttrDto.toProductAttr(
) = ProductAttrModel(
    name = name,
    visible = visible,
    id = id,
    options = options,
    variation = variation
)