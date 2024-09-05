package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun AppListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: StringResource,
    icon: ImageVector? = null,
) {
    ListItem(
        modifier = modifier
            .clickable { onClick() },
        leadingContent =
        icon?.let {
            {
                Icon(
                    imageVector = icon, contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
            leadingIconColor = Color.Gray,
        ),

        headlineContent = {
            Text(
                stringResource(label),
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
    )
}
