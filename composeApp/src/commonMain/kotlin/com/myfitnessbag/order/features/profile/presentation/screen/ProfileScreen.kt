package com.myfitnessbag.order.features.profile.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.presentation.compose.AppListItem
import com.myfitnessbag.order.core.presentation.compose.EventCollector
import com.myfitnessbag.order.core.presentation.design.EmailIcon
import com.myfitnessbag.order.core.presentation.design.FAQIcon
import com.myfitnessbag.order.core.presentation.design.LangIcon
import com.myfitnessbag.order.core.presentation.design.OrdersIcon
import com.myfitnessbag.order.core.presentation.design.PolicyIcon
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.presentation.design.TermsIcon
import com.myfitnessbag.order.features.profile.presentation.viewmodel.ProfileActions
import com.myfitnessbag.order.features.profile.presentation.viewmodel.ProfileEvents
import com.myfitnessbag.order.features.profile.presentation.viewmodel.ProfileState
import com.myfitnessbag.order.features.profile.presentation.viewmodel.ProfileViewModel
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.faqs
import myfitnessbag.composeapp.generated.resources.language
import myfitnessbag.composeapp.generated.resources.login_or_create_an_account
import myfitnessbag.composeapp.generated.resources.orders
import myfitnessbag.composeapp.generated.resources.privacy_policy
import myfitnessbag.composeapp.generated.resources.terms_conditions
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun ProfileScreenRoot(
    modifier: Modifier = Modifier,
    onShowSnackBar: (String) -> Unit,
    viewmodel: ProfileViewModel = koinViewModel(),
    onGoAccount: () -> Unit,
) {

    EventCollector(
        viewmodel.event,
    ) { event ->
        when (event) {
            is ProfileEvents.Error -> onShowSnackBar(event.error.getAsString())
        }
    }

    val state by viewmodel.state.collectAsState()
    ProfileScreen(
        modifier = modifier,
        state = state,
        onAction = viewmodel::onAction,
        onGoAccount = onGoAccount,
    )
}


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileState,
    onAction: (ProfileActions) -> Unit,
    onGoAccount: () -> Unit,
) {


    Column(
        modifier = modifier.fillMaxSize()
            .padding(Spacing.lg)
            .verticalScroll(rememberScrollState()),
    ) {


        state.user?.let {

            Text(
                "${it.firstName} ${it.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(Spacing.md))
            AppListItem(
                label = Res.string.account,
                icon = EmailIcon,
                onClick = {
                    onGoAccount()
                }
            )

        } ?: TextButton(
            onClick = {},
        ) {
            Text(
                stringResource(
                    Res.string.login_or_create_an_account,
                ),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        AppListItem(
            label = Res.string.language,
            icon = LangIcon,
            onClick = {
                onAction(ProfileActions.ChangeLocal)
            }
        )
        AppListItem(
            label = Res.string.terms_conditions,
            icon = TermsIcon,
            onClick = {}
        )
        AppListItem(
            label = Res.string.privacy_policy,
            icon = PolicyIcon,
            onClick = {}
        )
        AppListItem(
            label = Res.string.faqs,
            icon = FAQIcon,
            onClick = {}
        )
    }

}


