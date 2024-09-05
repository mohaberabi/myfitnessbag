package com.myfitnessbag.order.features.layout.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.SearchTextField
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import com.myfitnessbag.order.features.layout.presentation.compose.AppBottomBar
import com.myfitnessbag.order.features.layout.presentation.viewmodel.LayoutState
import com.myfitnessbag.order.features.layout.presentation.viewmodel.LayoutViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeLayoutRoot(
    modifier: Modifier = Modifier,
    onBottomNavigated: (LayoutItem) -> Unit,
    currentBottomRoute: LayoutItem,
    onGoSearch: () -> Unit,
    viewmodel: LayoutViewModel = koinViewModel(),
    onCartClick: () -> Unit,
    dynamicContent: @Composable (PaddingValues) -> Unit,
) {

    val state by viewmodel.state.collectAsState()
    HomeLayout(
        modifier = modifier,
        currentRoute = currentBottomRoute,
        onBottomNavigated = onBottomNavigated,
        dynamicContent = dynamicContent,
        onGoSearch = onGoSearch,
        layoutState = state,
        onCartClick = onCartClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    dynamicContent: @Composable (PaddingValues) -> Unit,
    currentRoute: LayoutItem,
    onBottomNavigated: (LayoutItem) -> Unit = {},
    onGoSearch: () -> Unit,
    layoutState: LayoutState,
    onCartClick: () -> Unit,
) {


    AppScaffold(
        topAppBar = {
            MainAppBar(
                color = MaterialTheme.colorScheme.primary,
                showBackButton = false,
                modifier = Modifier.wrapContentHeight(),
                isCenter = currentRoute == LayoutItem.Home,
                titleContent = {
                    if (currentRoute == LayoutItem.Home) {
                        SearchTextField(
                            hint = "Search Product",
                            enabled = false,
                            onClick = onGoSearch
                        )
                    } else {
                        Text(
                            stringResource(
                                currentRoute.label,
                            ),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }
            )
        },
        modifier = modifier,
        bottomAppBar = {
            AppBottomBar(
                top = currentRoute,
                cartCount = layoutState.cartCount,
                onClick = { item ->
                    if (item == LayoutItem.Cart) {
                        onCartClick()
                    } else {
                        onBottomNavigated(item)
                    }
                },
            )
        }
    ) { padding ->
        dynamicContent(padding)
    }
}