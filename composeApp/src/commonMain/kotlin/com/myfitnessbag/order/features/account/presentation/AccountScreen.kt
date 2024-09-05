package com.myfitnessbag.order.features.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myfitnessbag.order.core.presentation.compose.AppListItem
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.design.Spacing
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.billing_info
import myfitnessbag.composeapp.generated.resources.orders
import myfitnessbag.composeapp.generated.resources.shipping_info
import org.jetbrains.compose.resources.stringResource


@Composable
fun AccountScreenRoot(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onGoAccount: () -> Unit,
    onGoShipping: () -> Unit,
    onGoBilling: () -> Unit,
) {

    AccountScreen(
        modifier = modifier,
        onGoBilling = onGoBilling,
        onBackClick = onBackClick,
        onGoAccount = onGoAccount,
        onGoShipping = onGoShipping
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onGoAccount: () -> Unit,
    onGoShipping: () -> Unit,
    onGoBilling: () -> Unit,
) {
    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.account),
                showBackButton = true,
                onBackClick = { onBackClick() }
            )
        }
    ) { padding ->
        Column(
            Modifier.padding(padding)
                .padding(Spacing.lg)
        ) {

            AppListItem(
                onClick = { onGoAccount() },
                label = Res.string.account
            )

            AppListItem(
                onClick = { onGoShipping() },
                label = Res.string.shipping_info
            )

            AppListItem(
                onClick = { onGoBilling() },
                label = Res.string.billing_info
            )
        }
    }

}