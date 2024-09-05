package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.myfitnessbag.order.core.presentation.design.Spacing


@Composable
fun <T> AppSimpleChip(
    modifier: Modifier = Modifier,
    item: T? = null,
    selected: Boolean = false,
    label: (T) -> String = { "" },
    onClick: (T?) -> Unit = {},
) {

    JetMartChip<T>(
        modifier = modifier,
        item = item,
        onClick = onClick,
        selected = selected,
        label = { currentItem ->
            currentItem?.let {
                Text(text = label(it))
            }
        }
    )
}


@Composable
fun <T> JetMartChip(
    modifier: Modifier = Modifier,
    onClick: (T?) -> Unit = {},
    item: T? = null,
    leading: @Composable (T?) -> Unit = {},
    trailing: @Composable (T?) -> Unit = {},
    label: @Composable (T?) -> Unit = {},
    selected: Boolean = false,
    borderColor: Color = MaterialTheme.colorScheme.background
) {
    val textColor = if (selected)
        MaterialTheme.colorScheme.primary else
        Color.Gray


    val containerColor =
        if (selected) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.primary.copy(
            alpha = 0.35f
        )
    AssistChip(
        modifier = modifier.padding(Spacing.xs),
//        border = AssistChipDefaults.assistChipBorder(
//            borderColor = borderColor,
//        ),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
            labelColor = textColor,
        ),
        onClick = {
            onClick(item)
        },
        leadingIcon = {
            leading(item)
        },
        label = {
            label(item)
        },
        trailingIcon = {
            trailing(item)
        }
    )

}


