package com.myfitnessbag.order.features.product_details.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.myfitnessbag.order.core.presentation.compose.AppBottomSheet
import com.myfitnessbag.order.core.presentation.compose.AppButton
import com.myfitnessbag.order.core.presentation.compose.AppLoader
import com.myfitnessbag.order.core.presentation.compose.AppRadio
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.CachedImage
import com.myfitnessbag.order.core.presentation.compose.CarouselSlider
import com.myfitnessbag.order.core.presentation.compose.EventCollector
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.ProductCompose
import com.myfitnessbag.order.core.presentation.compose.ReadMoreText
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel
import com.myfitnessbag.order.features.product_details.presentation.compose.ProductDetailsCartAdder
import com.myfitnessbag.order.features.product_details.presentation.compose.ProductSelectorBuilder
import com.myfitnessbag.order.features.product_details.presentation.compose.VariationSelector
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailActions
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailEvent
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailState
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailStatus
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.add_to_cart
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProductDetailsScreenRoot(
    modifier: Modifier = Modifier,
    productId: Int,
    onBack: () -> Unit,
    viewmodel: ProductDetailViewModel = koinViewModel(),
    onProductClick: (Int) -> Unit,
    onShowSnackBar: (String) -> Unit,
) {

    EventCollector(
        viewmodel.event,
    ) { event ->
        when (event) {
            is ProductDetailEvent.ErrorAddToCart -> onShowSnackBar(event.error.getAsString())
            is ProductDetailEvent.ErrorUpdateCart -> onShowSnackBar(event.error.getAsString())
        }
    }
    LaunchedEffect(Unit) {
        viewmodel.onAction(ProductDetailActions.GetData(productId))
    }
    val state by viewmodel.state.collectAsState()
    ProductDetailsScreen(
        onBack = onBack,
        state = state,
        onProductClick = onProductClick,
        onAction = viewmodel::onAction
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    state: ProductDetailState,
    onBack: () -> Unit,
    onProductClick: (Int) -> Unit,
    onAction: (ProductDetailActions) -> Unit,
) {


    AppScaffold(
        topAppBar = {
            MainAppBar(
                onBackClick = onBack,
                showBackButton = true,
                title = ""
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (state.state) {
                ProductDetailStatus.Initial -> Unit
                ProductDetailStatus.Loading -> AppLoader()
                ProductDetailStatus.Error -> Text("Error")
                ProductDetailStatus.Populated -> {
                    LazyColumn {
                        state.product?.let {
                            with(it) {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .padding(top = padding.calculateTopPadding())
                                            .padding(Spacing.lg),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        CarouselSlider(
                                            items = images,
                                        ) { img ->
                                            CachedImage(
                                                model = img.src,
                                                modifier = Modifier.fillMaxWidth()
                                                    .height(225.dp)
                                                    .padding(horizontal = Spacing.sm),
                                            )
                                        }
                                        Spacer(Modifier.height(Spacing.sm))
                                        Text(
                                            product.name,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Spacer(Modifier.height(Spacing.sm))
                                        Text(
                                            "${product.price} EGP",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.primary,
                                            )
                                        )
                                        Spacer(Modifier.height(Spacing.lg))
                                        state.foundCartItem?.let { found ->
                                            with(found) {
                                                ProductDetailsCartAdder(
                                                    onIncrement = {
                                                        onAction(
                                                            ProductDetailActions.UpdateCartItem(
                                                                decrement = false
                                                            )
                                                        )
                                                    },
                                                    onDecrement = {
                                                        onAction(
                                                            ProductDetailActions.UpdateCartItem(
                                                                decrement = true
                                                            )
                                                        )
                                                    },
                                                    qty = qty.toString()
                                                )
                                            }

                                        } ?: AppButton(
                                            modifier = Modifier.fillMaxWidth(),
                                            loading = state.loadingCart,
                                            onClick = {
                                                onAction(ProductDetailActions.AddToCart)
                                            },
                                            label = stringResource(Res.string.add_to_cart)
                                        )

                                        ReadMoreText(
                                            description,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )

                                        Spacer(Modifier.height(Spacing.xlg))
                                        if (state.related.isNotEmpty()) {
                                            Text(
                                                "Related Products",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )

                                        }
                                    }
                                }
                                item {
                                    LazyRow {
                                        items(
                                            state.related,
                                        ) { item ->
                                            ProductCompose(
                                                product = item,
                                                modifier = Modifier.width(200.dp),
                                                onClick = { onProductClick(item.id) }
                                            )
                                        }
                                    }
                                }

                                item {
                                    Spacer(Modifier.height(Spacing.sm))
                                }
                            }


                        }


                    }
                }
            }


//            state.scopedVariant?.let { scoped ->
//
//                AppBottomSheet(
//                    onDismiss = { onAction(ProductDetailActions.DismissVariantSheet) },
//                ) {
//                    ProductSelectorBuilder(
//                        onSave = { option ->
//                            onAction(ProductDetailActions.OnVariantSaved(option))
//
//                        },
//                        variation = scoped,
//                    )
//                }

        }


    }

}