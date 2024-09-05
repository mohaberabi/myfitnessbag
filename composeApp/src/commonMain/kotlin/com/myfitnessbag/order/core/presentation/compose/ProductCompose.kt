package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.presentation.compose.ext.shimmerEffect
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.util.extensions.noRippleClickable
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.add_to_cart
import org.jetbrains.compose.resources.stringResource


@Composable
fun ProductCompose(
    modifier: Modifier = Modifier,
    product: ProductListingModel,
    onClick: () -> Unit = {},
) {


    ElevatedCard(
        modifier = modifier
            .padding(Spacing.sm)
            .fillMaxWidth()
            .height(265.dp)
            .noRippleClickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CachedImage(
                model = product.images.let {
                    if (it.isEmpty()) "" else it.first().src
                },
                size = 100.dp,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(Spacing.sm))
            Text(
                product.name,
                maxLines = 3,
                modifier = Modifier.padding(horizontal = Spacing.md)
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            Spacer(Modifier.height(Spacing.sm))


            product.stockQuantity?.let {

                if (it > 0) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Default.Done,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(Spacing.md))
                        Text(
                            "In stock",
                            style = MaterialTheme.typography.bodyMedium.copy(
                            )
                        )
                    }

                } else {
                    Text(
                        "Out of stock",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } ?: Text(
                "Out of stock",
                style = MaterialTheme.typography.bodyMedium
            )


            Spacer(Modifier.height(Spacing.md))


            product.salePrice?.let {
                Text(
                    it,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                    )
                )
            }
            Text(
                "${product.price} EGP",
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(Modifier.height(Spacing.xs))

        }
    }

}

@Composable
fun ProductShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(265.dp)
            .padding(Spacing.sm)
            .clip(RoundedCornerShape(Spacing.sm))
            .fillMaxWidth()
            .shimmerEffect()
    )
}