package com.myfitnessbag.order.features.listing.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.domain.model.cartTotal
import com.myfitnessbag.order.core.presentation.compose.AppButton
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.CartTotalBox
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.ProductCompose
import com.myfitnessbag.order.core.presentation.compose.ProductShimmer
import com.myfitnessbag.order.core.presentation.compose.ext.shimmerEffect
import com.myfitnessbag.order.core.presentation.compose.pullrefresh.AppPaginator
import com.myfitnessbag.order.core.presentation.compose.pullrefresh.AppPullRefresh
import com.myfitnessbag.order.core.presentation.compose.pullrefresh.appScrollable
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.util.extensions.noRippleClickable
import com.myfitnessbag.order.features.listing.presentation.viewmodel.ListingActions
import com.myfitnessbag.order.features.listing.presentation.viewmodel.ListingState
import com.myfitnessbag.order.features.listing.presentation.viewmodel.ListingViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ListingScreenRoot(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    categoryIndex: Int,
    categories: List<CategoryModel>,
    viewmodel: ListingViewModel = koinViewModel(),
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(
        Unit,
    ) {
        if (state.categories.isEmpty()) {
            viewmodel.onAction(
                ListingActions.DataChanged(
                    categories = categories,
                    index = categoryIndex,
                )
            )

        }

    }
    ListingScreen(
        modifier = modifier,
        state = state,
        onAction = viewmodel::onAction,
        onGoBack = onGoBack,
        onProductClick = onProductClick,
        onCartClick = onCartClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingScreen(
    modifier: Modifier = Modifier,
    onAction: (ListingActions) -> Unit,
    state: ListingState,
    onGoBack: () -> Unit,
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
) {


    val rowScrollState = rememberLazyListState()
    val scrollState = rememberLazyGridState()
    LaunchedEffect(
        state.categoryIndex,
    ) {
        scrollState.scrollToItem(0)
        rowScrollState.animateScrollToItem(state.categoryIndex)
    }
    AppScaffold(
        bottomAppBar = {
            CartTotalBox(
                total = state.cartTotal,
                onClick = onCartClick,
            )
        },
        topAppBar = {
            MainAppBar(
                onBackClick = onGoBack,
                showBackButton = true,
                title = "",
                titleContent = {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = Spacing.lg),
                        state = rowScrollState,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        items(
                            state.categories.size,
                        ) { index ->
                            Column(
                                modifier = Modifier.wrapContentHeight()
                                    .padding(horizontal = Spacing.sm)
                                    .noRippleClickable {
                                        onAction(
                                            ListingActions.CategoryChanged(index)
                                        )
                                    }
                            ) {
                                Text(
                                    state.categories[index].name,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = index.let {
                                            if (it == state.categoryIndex)
                                                FontWeight.Bold
                                            else FontWeight.Normal
                                        },
                                        color = index.let {
                                            if (it == state.categoryIndex)
                                                MaterialTheme.colorScheme.primary
                                            else Color.Gray
                                        }
                                    ),
                                )
                                Box(
                                    Modifier.fillMaxWidth()
                                        .clip(RoundedCornerShape(Spacing.xs))
                                        .height(Spacing.lg).background(index.let {
                                            if (it == state.categoryIndex)
                                                MaterialTheme.colorScheme.primary
                                            else Color.Transparent
                                        })
                                )
                            }

                        }

                    }
                }
            )
        }
    )
    { padding ->
        Box(
            Modifier.fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            when {
                !state.error.isEmpty -> Text("Error")
                else -> {
                    AppPaginator(
                        onPaging = { onAction(ListingActions.PaginateItems) },
                        scrollable = scrollState.appScrollable,
                    ) {
                        LazyVerticalGrid(
                            GridCells.Fixed(2),
                            modifier = Modifier.padding(Spacing.lg),
                            state = scrollState,
                        ) {
                            if (state.items.isNotEmpty()) {
                                items(
                                    state.items,
                                ) { product ->
                                    ProductCompose(
                                        product = product,
                                        onClick = { onProductClick(product.id) },
                                    )
                                }
                            }
                            if (state.loading) {
                                items(10) {
                                    ProductShimmer()
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}