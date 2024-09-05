package com.myfitnessbag.order.core.util.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


fun Modifier.noRippleClickable(
    onClick: () -> Unit = {},
) = composed {
    clickable(
        indication = null,
        interactionSource = MutableInteractionSource(),
        onClick = onClick
    )
}