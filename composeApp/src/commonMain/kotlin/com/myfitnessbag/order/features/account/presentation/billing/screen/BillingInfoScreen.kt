package com.myfitnessbag.order.features.account.presentation.billing.screen

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
import com.myfitnessbag.order.core.presentation.compose.EventCollector
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.PrimaryTextField
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoActions
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoEvents
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoState
import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.address
import myfitnessbag.composeapp.generated.resources.address1
import myfitnessbag.composeapp.generated.resources.address2
import myfitnessbag.composeapp.generated.resources.billing_info
import myfitnessbag.composeapp.generated.resources.city
import myfitnessbag.composeapp.generated.resources.country
import myfitnessbag.composeapp.generated.resources.email
import myfitnessbag.composeapp.generated.resources.last_name
import myfitnessbag.composeapp.generated.resources.name
import myfitnessbag.composeapp.generated.resources.phone
import myfitnessbag.composeapp.generated.resources.save
import myfitnessbag.composeapp.generated.resources.update
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BillingInfoScreenRoot(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    viewModel: BillingInfoViewModel = koinViewModel(),
    onShowSnackBar: (String) -> Unit,
) {
    EventCollector(viewModel.event) { event ->
        when (event) {
            is BillingInfoEvents.Error -> onShowSnackBar(event.error.getAsString())
            BillingInfoEvents.Updated -> onShowSnackBar(getString(Res.string.update))
        }
    }
    val state by viewModel.state.collectAsState()
    BillingInfoScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        onGoBack = onGoBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillingInfoScreen(
    modifier: Modifier = Modifier,
    onAction: (BillingInfoActions) -> Unit,
    state: BillingInfoState,
    onGoBack: () -> Unit,
) {

    AppScaffold(
        modifier = modifier,
        topAppBar = {
            MainAppBar(
                onBackClick = { onGoBack() },
                showBackButton = true,
                title = stringResource(Res.string.billing_info)
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
                val billing = user.billing
                PrimaryTextField(
                    value = billing.email,
                    isReadOnly = true,
                    label = stringResource(Res.string.email)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    PrimaryTextField(
                        value = billing.firstName,
                        onChanged = { },
                        width = 0.5f,
                        label = stringResource(Res.string.name)
                    )
                    Spacer(Modifier.width(Spacing.sm))
                    PrimaryTextField(
                        value = billing.lastName,
                        onChanged = { },
                        width = 1f,
                        label = stringResource(Res.string.last_name)
                    )
                }

                PrimaryTextField(
                    value = billing.country,
                    onChanged = { },
                    label = stringResource(Res.string.country)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    PrimaryTextField(
                        value = billing.state,
                        onChanged = { },
                        width = 0.5f,
                        label = stringResource(Res.string.name)
                    )
                    Spacer(Modifier.width(Spacing.sm))
                    PrimaryTextField(
                        value = billing.city,
                        onChanged = { },
                        width = 1f,
                        label = stringResource(Res.string.city)
                    )
                }
                PrimaryTextField(
                    value = billing.address1,
                    onChanged = { },
                    label = stringResource(Res.string.address1)
                )
                PrimaryTextField(
                    value = billing.address2,
                    onChanged = { },
                    label = stringResource(Res.string.address)
                )
                PrimaryTextField(
                    value = billing.phone,
                    onChanged = { },
                    label = stringResource(Res.string.phone)
                )
                AppButton(
                    onClick = {},
                    label = stringResource(Res.string.save)
                )
            }


        }

    }
}