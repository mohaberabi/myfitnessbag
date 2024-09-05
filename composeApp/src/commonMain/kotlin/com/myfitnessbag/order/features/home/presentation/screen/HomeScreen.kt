package com.myfitnessbag.order.features.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.presentation.compose.AppLoader
import com.myfitnessbag.order.core.presentation.compose.ext.shimmerEffect
import com.myfitnessbag.order.core.presentation.compose.ext.spannedItem
import com.myfitnessbag.order.core.presentation.compose.pullrefresh.AppPullRefresh
import com.myfitnessbag.order.core.presentation.compose.pullrefresh.appScrollable
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.home.presentation.compose.CategoryCompose
import com.myfitnessbag.order.features.home.presentation.compose.CategoryHomeShimmer
import com.myfitnessbag.order.features.home.presentation.viewmodel.HomeActions
import com.myfitnessbag.order.features.home.presentation.viewmodel.HomeState
import com.myfitnessbag.order.features.home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onCategoryClick: (Int, List<CategoryModel>) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    HomeScreen(
        modifier = modifier,
        state = state,
        onCategoryClick = onCategoryClick,
        onAction = viewModel::onAction
    )

}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onCategoryClick: (Int, List<CategoryModel>) -> Unit,
    onAction: (HomeActions) -> Unit = {},
) {

    val lazyState = rememberLazyGridState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.lg),
    ) {

        when {
            !state.error.isEmpty -> Text("Error")
            state.loading -> AppLoader()
            else -> {
                AppPullRefresh(
                    onRefresh = { onAction(HomeActions.Refresh) },
                    scrollable = lazyState.appScrollable,
                ) {
                    LazyVerticalGrid(
                        state = lazyState,
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(0.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        spannedItem(3) {
                            Text(
                                "Shop by category",
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }

                        items(
                            state.categories.size,
                        ) { index ->
                            CategoryCompose(
                                category = state.categories[index],
                                onClick = { onCategoryClick(index, state.categories) },
                            )
                        }
                    }
                }

            }

        }

    }
}