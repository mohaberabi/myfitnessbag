package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myfitnessbag.order.core.presentation.design.Spacing


@Composable
fun CartTotalBox(
    modifier: Modifier = Modifier,
    total: Double,
    onClick: () -> Unit,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        AppButton(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = Spacing.xs),
            enabled = total > 0,
            label = "Cart total : $total EGP",
            onClick = onClick,
        )
    }
}