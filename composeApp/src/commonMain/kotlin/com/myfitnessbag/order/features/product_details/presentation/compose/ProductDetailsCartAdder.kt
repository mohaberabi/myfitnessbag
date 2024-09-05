package com.myfitnessbag.order.features.product_details.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.TextUnit
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.util.extensions.noRippleClickable


@Composable
fun ProductDetailsCartAdder(
    modifier: Modifier = Modifier,
    qty: String,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    textSize: TextUnit = MaterialTheme.typography.headlineMedium.fontSize
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            "+",
            modifier = Modifier.noRippleClickable(onIncrement),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontSize = textSize
            )
        )

        Text(
            qty,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = textSize
            )
        )
        Text(
            "-",
            modifier = Modifier.noRippleClickable(onDecrement),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontSize = textSize
            )
        )
    }
}