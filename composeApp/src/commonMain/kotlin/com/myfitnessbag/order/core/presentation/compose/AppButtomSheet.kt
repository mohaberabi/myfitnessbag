package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    ModalBottomSheet(
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        modifier = modifier,
        content = content
    )
}