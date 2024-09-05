package com.myfitnessbag.order.features.search.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.myfitnessbag.order.core.presentation.compose.AppLoader
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.ProductCompose
import com.myfitnessbag.order.core.presentation.compose.SearchTextField
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.search.presentation.viewmodel.SearchProductActions
import com.myfitnessbag.order.features.search.presentation.viewmodel.SearchProductState
import com.myfitnessbag.order.features.search.presentation.viewmodel.SearchProductStatus
import com.myfitnessbag.order.features.search.presentation.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    viewModel: SearchViewModel = koinViewModel(),
    onProductClick: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    SearchScreen(
        modifier = modifier,
        onGoBack = onGoBack,
        state = state,
        onAction = viewModel::onAction,
        onProductClick = onProductClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    state: SearchProductState,
    onAction: (SearchProductActions) -> Unit,
    onProductClick: (Int) -> Unit,
) {


    AppScaffold(
        modifier = modifier,
        topAppBar = {
            MainAppBar(
                navigationIconColor = MaterialTheme.colorScheme.onPrimary,
                titleContent = {
                    SearchTextField(
                        enabled = true,
                        value = state.query,
                        onValueChanged = { onAction(SearchProductActions.QueryChanged(it)) },
                    )
                },
                onBackClick = onGoBack,
                showBackButton = true,
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = padding.calculateTopPadding())
                .padding(Spacing.lg)
        ) {
            when (state.state) {

                SearchProductStatus.Error -> Text("Error")
                SearchProductStatus.Populated -> {

                    if (state.items.isEmpty()) {
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                        ) {
                            items(state.items) { item ->
                                ProductCompose(
                                    product = item,
                                    onClick = { onProductClick(item.id) }
                                )
                            }
                        }
                    }
                }

                SearchProductStatus.Loading -> AppLoader()
                else -> Unit
            }


        }


    }

}