package com.myfitnessbag.order.features.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myfitnessbag.order.core.domain.model.asUiText
import com.myfitnessbag.order.core.presentation.compose.AppButton
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.presentation.compose.EventCollector
import com.myfitnessbag.order.core.presentation.compose.MainAppBar
import com.myfitnessbag.order.core.presentation.compose.PasswordTextField
import com.myfitnessbag.order.core.presentation.compose.PrimaryTextField
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.features.auth.presentation.viewmodel.AuthActions
import com.myfitnessbag.order.features.auth.presentation.viewmodel.AuthEvent
import com.myfitnessbag.order.features.auth.presentation.viewmodel.AuthState
import com.myfitnessbag.order.features.auth.presentation.viewmodel.AuthViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.already_have_an_account
import myfitnessbag.composeapp.generated.resources.create_account
import myfitnessbag.composeapp.generated.resources.dont_have_Account
import myfitnessbag.composeapp.generated.resources.email
import myfitnessbag.composeapp.generated.resources.example_jetmart_com
import myfitnessbag.composeapp.generated.resources.first_name
import myfitnessbag.composeapp.generated.resources.hello_there
import myfitnessbag.composeapp.generated.resources.last_name
import myfitnessbag.composeapp.generated.resources.login
import myfitnessbag.composeapp.generated.resources.login_or_create_an_account
import myfitnessbag.composeapp.generated.resources.skip
import myfitnessbag.composeapp.generated.resources.welcome_again
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreenRoot(
    modifier: Modifier = Modifier,
    onShowSnackBar: (String) -> Unit,
    viewModel: AuthViewModel = koinViewModel(),
    onGoHomeScreen: () -> Unit,
    canSkip: Boolean = true,
    onGoBack: () -> Unit = {},
) {
    EventCollector(flow = viewModel.event) { event ->
        when (event) {
            AuthEvent.AuthDone -> onGoHomeScreen()
            is AuthEvent.Error -> onShowSnackBar(event.error.getAsString())
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        onBackClick = onGoBack,
        canSkip = canSkip,
        modifier = modifier,
        onAction = { act ->
            if (act is AuthActions.OnSkip) {
                onGoHomeScreen()
            } else {
                viewModel.onAction(act)
            }
        },
        state = state,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onAction: (AuthActions) -> Unit,
    canSkip: Boolean = true,
    onBackClick: () -> Unit = {},
    onSkip: () -> Unit = {}
) {

    val localKeyboard = LocalSoftwareKeyboardController.current


    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = false,
                title = stringResource(Res.string.login_or_create_an_account)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Spacing.lg),
                ) {


                    Text(
                        text = stringResource(greeting(state.isLogin)),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    PrimaryTextField(
                        label = stringResource(Res.string.email),
                        value = state.email,
                        onChanged = { onAction(AuthActions.OnEmailChanged(it)) },
                        placeHolder = "example@example.com",
                        error = state.emailValid.status.notValid,
                        errorText = state.emailValid.reason?.asUiText()?.asString()
                    )

                    PasswordTextField(
                        value = state.password,
                        error = state.passwordValid.status.notValid,
                        errorText = state.passwordValid.reason?.asUiText()?.asString(),
                        onChange = { onAction(AuthActions.OnPasswordChanged(it)) },
                    )


                    if (!state.isLogin)
                        Row(
                        ) {
                            PrimaryTextField(
                                value = state.name,
                                width = 0.5f,
                                onChanged = { onAction(AuthActions.OnNameChanged(it)) },
                                label = stringResource(Res.string.first_name),
                                placeHolder = "My Fitness",
                            )
                            Spacer(Modifier.width(Spacing.sm))
                            PrimaryTextField(
                                value = state.lastName,
                                width = 1f,
                                onChanged = { onAction(AuthActions.OnLastNameChanged(it)) },
                                label = stringResource(Res.string.last_name),
                                placeHolder = "Bag",
                            )
                        }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(authWayLabel(state.isLogin)),
                        )
                        TextButton(
                            onClick = {
                                onAction(AuthActions.OnToggleAuthWay)
                            },
                        ) {

                            Text(text = stringResource(authWayButtonLabel(state.isLogin)))
                        }
                    }

                    AppButton(
                        loading = state.loading,
                        enabled = state.buttonEnabled,
                        onClick = {
                            localKeyboard?.hide()
                            onAction(AuthActions.OnLoginClick)
                        },
                        label = stringResource(loginLabel(state.isLogin))
                    )


                }
            }
        }
    }
}

fun greeting(
    isLogin: Boolean,
): StringResource {
    return if (isLogin) Res.string.welcome_again
    else Res.string.hello_there
}

fun authWayButtonLabel(
    isLogin: Boolean,
): StringResource {
    return if (isLogin) Res.string.create_account
    else Res.string.login

}

fun authWayLabel(
    isLogin: Boolean,
): StringResource {
    return if (isLogin) Res.string.dont_have_Account
    else Res.string.already_have_an_account
}


fun loginLabel(
    isLogin: Boolean,
): StringResource {
    return if (isLogin) {
        Res.string.login
    } else {
        Res.string.create_account
    }
}
