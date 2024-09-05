package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.img_placholder


@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    isPreview: Boolean = false,
    model: Any,
    borderRadius: Dp = 15.dp
) {

    val currentModel = if (isPreview) Res.drawable.img_placholder else model
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(borderRadius)),
        model = currentModel,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        loading = {
            ImagePlaceHolder(size = 100.dp)
        },
        error = {
            ImagePlaceHolder(size = 100.dp)
        }
    )


}

@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    isPreview: Boolean = false,
    model: Any,
    size: Dp = 110.dp,
    borderRadius: Dp = 15.dp
) {

    val currentModel = if (isPreview) Res.drawable.img_placholder else model
    SubcomposeAsyncImage(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(borderRadius)),
        model = currentModel,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        loading = {
            ImagePlaceHolder(size = size)
        },
        error = {
            ImagePlaceHolder(size = size)
        }
    )


}