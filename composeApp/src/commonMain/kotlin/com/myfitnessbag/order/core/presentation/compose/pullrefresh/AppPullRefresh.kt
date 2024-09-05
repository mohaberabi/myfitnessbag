package com.myfitnessbag.order.core.presentation.compose.pullrefresh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.presentation.design.Spacing
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AppPullRefresh(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    isPaging: Boolean = false,
    onRefresh: () -> Unit,
    scrollable: AppScrollable,
    onPaging: (() -> Unit)? = null,
    content: @Composable AppScrollable.() -> Unit,
) {
    val showIndicator = remember {
        mutableStateOf(false)
    }

    scrollable.pullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh,
        keepIndicatorMillis = 3000,
        shouldShowIndicator = true,
        isIndicatorVisible = showIndicator
    )
    scrollable.pagination(
        onPaging = {
            onPaging?.invoke()
        },
        isPaging = isPaging,
        enabled = onPaging != null,
    )


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        content(
            scrollable,
        )
        if (showIndicator.value) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .size(36.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    modifier = Modifier
                        .size(Spacing.xlg)
                        .padding(Spacing.xs)
                )
            }
        }

    }
}

@Composable
fun AppPaginator(
    modifier: Modifier = Modifier,
    isPaging: Boolean? = null,
    scrollable: AppScrollable,
    onPaging: () -> Unit,
    content: @Composable AppScrollable.() -> Unit,
) {
    val showIndicator = remember {
        mutableStateOf(false)
    }


    scrollable.pagination(
        onPaging = onPaging,
        isPaging = isPaging ?: false,
        enabled = true,
    )


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        content(
            scrollable,
        )
        if (showIndicator.value) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .size(36.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    modifier = Modifier
                        .size(Spacing.xlg)
                        .padding(Spacing.xs)
                )
            }
        }

    }
}