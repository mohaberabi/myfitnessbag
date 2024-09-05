package com.myfitnessbag.order.features.account.presentation.shipping.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.myfitnessbag.order.core.presentation.compose.AppButton
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.PrimaryTextField
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoActions
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoState
import com.myfitnessbag.order.features.account.presentation.shipping.viewmodel.ShippingInfoActions
import com.myfitnessbag.order.features.account.presentation.shipping.viewmodel.ShippingInfoState
import com.myfitnessbag.order.features.account.presentation.shipping.viewmodel.ShippingInfoViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.address1
import myfitnessbag.composeapp.generated.resources.address2
import myfitnessbag.composeapp.generated.resources.city
import myfitnessbag.composeapp.generated.resources.country
import myfitnessbag.composeapp.generated.resources.email
import myfitnessbag.composeapp.generated.resources.last_name
import myfitnessbag.composeapp.generated.resources.name
import myfitnessbag.composeapp.generated.resources.save
import myfitnessbag.composeapp.generated.resources.shipping_info
import myfitnessbag.composeapp.generated.resources.state
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ShippingInfoScreenRoot(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    viewModel: ShippingInfoViewModel = koinViewModel(),
    onShowSnackBar: (String) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    ShippingInfoScreen(
        modifier = modifier,
        onAction = viewModel::onAction,
        onGoBack = onGoBack,
        state = state
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingInfoScreen(
    modifier: Modifier = Modifier,
    onAction: (ShippingInfoActions) -> Unit,
    state: ShippingInfoState,
    onGoBack: () -> Unit,
) {


    AppScaffold(
        modifier = modifier,
        topAppBar = {
            MainAppBar(
                onBackClick = { onGoBack() },
                showBackButton = true,
                title = stringResource(Res.string.shipping_info)
            )
        }
    ) {

            padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(Spacing.lg)
                .verticalScroll(rememberScrollState())
        ) {


            state.user?.let { user ->
                val shipping = user.shipping
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    PrimaryTextField(
                        value = shipping.firstName,
                        onChanged = { },
                        width = 0.5f,
                        label = stringResource(Res.string.name)
                    )
                    Spacer(Modifier.width(Spacing.sm))
                    PrimaryTextField(
                        value = shipping.lastName,
                        onChanged = { },
                        width = 1f,
                        label = stringResource(Res.string.last_name)
                    )
                }
                PrimaryTextField(
                    value = shipping.country,
                    onChanged = { },
                    label = stringResource(Res.string.country)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    PrimaryTextField(
                        value = shipping.state,
                        onChanged = { },
                        width = 0.5f,
                        label = stringResource(Res.string.state)
                    )
                    Spacer(Modifier.width(Spacing.sm))
                    PrimaryTextField(
                        value = shipping.city,
                        onChanged = { },
                        width = 1f,
                        label = stringResource(Res.string.city)
                    )
                }
                PrimaryTextField(
                    value = shipping.address1,
                    onChanged = { },
                    label = stringResource(Res.string.address1)
                )
                PrimaryTextField(
                    value = shipping.address2,
                    onChanged = { },
                    label = stringResource(Res.string.address2)
                )

                AppButton(
                    onClick = {},
                    label = stringResource(Res.string.save)
                )
            }

        }

    }
}