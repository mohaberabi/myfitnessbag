package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.myfitnessbag.order.core.presentation.design.BackIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    title: String = "",
    titleContent: (@Composable () -> Unit)? = null,
    onBackClick: () -> Unit = {},
    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    actions: @Composable RowScope.() -> Unit = {},
    isCenter: Boolean = true,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    color: Color = MaterialTheme.colorScheme.background,
    navigationIconColor: Color = MaterialTheme.colorScheme.onBackground,

    ) {


    if (isCenter) {
        CenterAppBar(
            modifier = modifier,
            color = color,
            title = {
                if (titleContent != null) titleContent()
                else AppBarTitle(
                    title = title,
                    color = titleColor
                )
            },
            showBackButton = showBackButton,
            onBackClick = onBackClick,
            scrollBehaviour = scrollBehaviour,
            navigationIconColor = navigationIconColor,
            actions = actions
        )
    } else {
        DefaultAppBar(
            modifier = modifier,
            color = color,
            title = {
                if (titleContent != null) titleContent()
                else AppBarTitle(
                    title = title,
                    color = titleColor
                )
            },
            showBackButton = showBackButton,
            onBackClick = onBackClick,
            navigationIconColor = navigationIconColor,
            scrollBehaviour = scrollBehaviour,
            actions = actions
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun DefaultAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background,
    navigationIconColor: Color = MaterialTheme.colorScheme.onBackground,

    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
) {

    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehaviour,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        navigationIcon = {
            if (showBackButton) {
                BackButton(
                    onBackClick = onBackClick,
                    tint = navigationIconColor
                )
            }
        },
        title =
        title
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background,
    navigationIconColor: Color = MaterialTheme.colorScheme.onBackground,

    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title,
        scrollBehavior = scrollBehaviour,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        navigationIcon = {
            if (showBackButton) {
                BackButton(
                    onBackClick = onBackClick,
                    tint = navigationIconColor
                )
            }
        },
        actions = actions
    )
}

@Composable
private fun AppBarTitle(
    title: String,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
        color = color,
    )
}

@Composable
private fun BackButton(
    onBackClick: () -> Unit = {},
    tint: Color = MaterialTheme.colorScheme.onBackground
) {
    IconButton(onClick = { onBackClick() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "",
            tint = tint,
        )
    }
}

