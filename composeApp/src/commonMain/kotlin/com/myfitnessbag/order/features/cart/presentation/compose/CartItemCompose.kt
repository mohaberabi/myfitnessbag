package com.myfitnessbag.order.features.cart.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.presentation.compose.CachedImage
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.product_details.presentation.compose.ProductDetailsCartAdder


@Composable
fun CartItemCompose(
    modifier: Modifier = Modifier,
    item: CartItemModel,
    onInc: () -> Unit,
    onDec: () -> Unit,
) {


    Column {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CachedImage(
                size = 65.dp,
                model = ""
            )
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    item.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        item.totalPrice.toString(),
                        modifier = Modifier.fillMaxWidth(0.7f),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )
                    ProductDetailsCartAdder(
                        modifier = Modifier.fillMaxWidth(1f),
                        onDecrement = onDec,
                        onIncrement = onInc,
                        qty = item.qty.toString(),
                        textSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
        }
        Spacer(Modifier.height(Spacing.md))
        HorizontalDivider(color = Color.LightGray)

    }

}