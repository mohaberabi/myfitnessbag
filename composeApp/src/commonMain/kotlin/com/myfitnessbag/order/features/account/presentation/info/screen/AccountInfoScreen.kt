package com.myfitnessbag.order.features.account.presentation.info.screen

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
import com.myfitnessbag.order.features.account.presentation.info.viewmodel.AccountInfoActions
import com.myfitnessbag.order.features.account.presentation.info.viewmodel.AccountInfoEvents
import com.myfitnessbag.order.features.account.presentation.info.viewmodel.AccountInfoState
import com.myfitnessbag.order.features.account.presentation.info.viewmodel.AccountInfoViewModel

import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.email
import myfitnessbag.composeapp.generated.resources.last_name
import myfitnessbag.composeapp.generated.resources.name
import myfitnessbag.composeapp.generated.resources.save
import myfitnessbag.composeapp.generated.resources.update
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AccountInfoScreenRoot(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    viewModel: AccountInfoViewModel = koinViewModel(),
    onShowSnackBar: (String) -> Unit,
) {
    EventCollector(viewModel.event) { event ->
        when (event) {
            is AccountInfoEvents.Error -> onShowSnackBar(event.error.getAsString())
            AccountInfoEvents.Updated -> onShowSnackBar(getString(Res.string.update))
        }
    }
    val state by viewModel.state.collectAsState()
    AccountInfoScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        onGoBack = onGoBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfoScreen(
    modifier: Modifier = Modifier,
    onAction: (AccountInfoActions) -> Unit,
    state: AccountInfoState,
    onGoBack: () -> Unit,
) {

    AppScaffold(
        modifier = modifier,
        topAppBar = {
            MainAppBar(
                onBackClick = { onGoBack() },
                showBackButton = true,
                title = stringResource(Res.string.account)
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
                PrimaryTextField(
                    value = user.email,
                    isReadOnly = true,
                    label = stringResource(Res.string.email)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    PrimaryTextField(
                        value = user.firstName,
                        onChanged = { onAction(AccountInfoActions.NameChanged(it)) },
                        width = 0.5f,
                        label = stringResource(Res.string.name)
                    )
                    Spacer(Modifier.width(Spacing.sm))
                    PrimaryTextField(
                        value = user.lastName,
                        onChanged = { onAction(AccountInfoActions.LastNameChanged(it)) },
                        width = 1f,
                        label = stringResource(Res.string.last_name)
                    )
                }


                AppButton(
                    onClick = {},
                    label = stringResource(Res.string.save)
                )
            }


        }

    }
}