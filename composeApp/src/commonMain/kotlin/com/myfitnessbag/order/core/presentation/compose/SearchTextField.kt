package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.presentation.design.Spacing


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    hint: String = "",
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    OutlinedTextField(
        enabled = enabled,
        maxLines = 1,
        singleLine = true,
        modifier = modifier.fillMaxWidth()
            .clickable { onClick?.invoke() }
            .height(50.dp),
        shape = RoundedCornerShape(Spacing.sm),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray,
            disabledContainerColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
        ),
        value = value,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.LightGray,
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search"
            )
        },
        onValueChange = onValueChanged,
    )
}