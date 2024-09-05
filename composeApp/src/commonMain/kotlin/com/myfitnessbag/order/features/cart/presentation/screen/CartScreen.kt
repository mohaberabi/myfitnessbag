package com.myfitnessbag.order.features.cart.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.CartTotalBox
import com.myfitnessbag.order.core.presentation.compose.EventCollector
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.cart.presentation.compose.CartItemCompose
import com.myfitnessbag.order.features.cart.presentation.viewmodel.CartActions
import com.myfitnessbag.order.features.cart.presentation.viewmodel.CartEvents
import com.myfitnessbag.order.features.cart.presentation.viewmodel.CartState
import com.myfitnessbag.order.features.cart.presentation.viewmodel.CartViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.cart
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CartScreenRoot(
    modifier: Modifier = Modifier,
    onShowSnackBar: (String) -> Unit,
    onGoBack: () -> Unit,
    viewModel: CartViewModel = koinViewModel()
) {
    EventCollector(
        viewModel.event
    ) { event ->
        when (event) {
            is CartEvents.Error -> onShowSnackBar(event.error.getAsString())
        }
    }
    val state by viewModel.state.collectAsState()
    CartScreen(
        onAction = viewModel::onAction,
        onBack = onGoBack,
        state = state
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    onAction: (CartActions) -> Unit,
    state: CartState,
    onBack: () -> Unit,
) {


    AppScaffold(
        bottomAppBar = {
            CartTotalBox(
                total = state.cartTotal,
                onClick = {},
            )
        },
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                isCenter = false,
                onBackClick = onBack,
                navigationIconColor = MaterialTheme.colorScheme.onPrimary,
                titleContent = {
                    Text(
                        stringResource(Res.string.cart),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                },
                color = MaterialTheme.colorScheme.primary,
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(Spacing.lg)
        ) {
            items(state.items) { item ->
                CartItemCompose(
                    item = item,
                    onDec = { onAction(CartActions.UpdateQty(id = item.id, decrement = true)) },
                    onInc = { onAction(CartActions.UpdateQty(id = item.id, decrement = false)) }
                )
            }
        }

    }

}