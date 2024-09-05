package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.myfitnessbag.order.core.presentation.design.Spacing


@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    error: Boolean = false,
    value: String = "",
    onChanged: (String) -> Unit = {},
    options: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    suffix: @Composable () -> Unit = {},
    label: String = "",
    placeHolder: String = "",
    isReadOnly: Boolean = false,
    visualTransformations: VisualTransformation = VisualTransformation.None,
    width: Float = 1f,
    errorText: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = Color.Gray,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        errorContainerColor = Color.Transparent
    )
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .wrapContentHeight()
            .padding(Spacing.sm),
    ) {
        Spacer(Modifier.height(Spacing.sm))
        TextField(
            isError = error,
            modifier = modifier
                .fillMaxWidth(width),
            placeholder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                )
            },
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = singleLine,
            readOnly = isReadOnly,
            keyboardOptions = options,
            visualTransformation = visualTransformations,
            enabled = true,
            trailingIcon = suffix,
            interactionSource = interactionSource,
            colors = colors,
            value = value,
            onValueChange = onChanged,
        )
        errorText?.let {
            Spacer(Modifier.height(Spacing.xs))
            Text(
                it,
                style = MaterialTheme
                    .typography.bodySmall
                    .copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }

}


