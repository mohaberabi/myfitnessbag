package com.myfitnessbag.order.features.product_details.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.util.extensions.noRippleClickable


@Composable
fun VariationSelector(
    modifier: Modifier = Modifier,
    title: String,
    value: String? = null,
    onClick: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(vertical = Spacing.md)
                .noRippleClickable(onClick),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                )
            )
            value?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            } ?: Text(
                "Select an option",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = Color.LightGray,
            modifier = Modifier.height(Spacing.sm)
        )

    }


}