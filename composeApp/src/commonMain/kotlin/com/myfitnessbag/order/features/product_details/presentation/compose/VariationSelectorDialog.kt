package com.myfitnessbag.order.features.product_details.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.myfitnessbag.order.core.presentation.compose.AppButton
import com.myfitnessbag.order.core.presentation.compose.AppSimpleRadio
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.product_details.data.source.remote.dto.ProductAttrDto
import com.myfitnessbag.order.features.product_details.domain.model.ProductAttrModel


@Composable
fun ProductSelectorBuilder(
    modifier: Modifier = Modifier,
    variation: ProductAttrModel,
    onSave: (String) -> Unit,
) {
    var selectedItem by remember {
        mutableStateOf<String?>(null)
    }
    Column(
        modifier = modifier.wrapContentSize()
            .padding(Spacing.sm)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Text(
            "Select ${variation.name}",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(Spacing.xlg))
        variation.options.forEach { option ->
            val selected = option == selectedItem
            AppSimpleRadio(
                modifier = Modifier.fillMaxWidth(),
                onChanged = { selectedItem = option },
                item = option,
                selected = selected,
                label = { option }
            )
        }

        Spacer(Modifier.height(Spacing.xlg))
        AppButton(
            modifier.fillMaxWidth()
                .padding(horizontal = Spacing.lg),
            enabled = selectedItem != null,
            label = "Select",
            onClick = { onSave(selectedItem!!) }
        )

    }
}