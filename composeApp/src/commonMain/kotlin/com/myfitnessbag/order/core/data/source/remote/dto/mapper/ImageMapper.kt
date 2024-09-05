package com.myfitnessbag.order.core.data.source.remote.dto.mapper

import com.myfitnessbag.order.core.data.source.remote.dto.ImageDto
import com.myfitnessbag.order.core.domain.model.ImageModel


fun ImageDto.toImageModel(
) = ImageModel(
    src = src,
)