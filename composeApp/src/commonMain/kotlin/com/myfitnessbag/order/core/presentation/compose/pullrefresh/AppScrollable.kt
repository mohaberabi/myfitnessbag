package com.myfitnessbag.order.core.presentation.compose.pullrefresh

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


interface AppScrollable {
    val firstVisibleIndex: Int
    val firstVisibleOffset: Int
    val isScrolling: Boolean
    val didReachEnd: Boolean
}

data class PaginateState(
    val isPaging: Boolean,
    val onPaging: () -> Unit,
    val enabled: Boolean,
)

data class PullRefreshState(
    val refreshing: Boolean,
    val onRefresh: () -> Unit,
    val keepIndicatorMillis: Long,
    val shouldShowIndicator: Boolean,
)

val LazyGridState.appScrollable: AppScrollable
    get() = object : AppScrollable {
        override val firstVisibleIndex: Int
            get() = firstVisibleItemIndex
        override val firstVisibleOffset: Int
            get() = firstVisibleItemScrollOffset
        override val isScrolling: Boolean
            get() = isScrollInProgress
        override val didReachEnd: Boolean
            get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    }


val LazyListState.appScrollable: AppScrollable
    get() = object : AppScrollable {
        override val firstVisibleIndex: Int
            get() = firstVisibleItemIndex
        override val firstVisibleOffset: Int
            get() = firstVisibleItemScrollOffset
        override val isScrolling: Boolean
            get() = isScrollInProgress
        override val didReachEnd: Boolean
            get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }


@Composable
fun AppScrollable.pagination(
    onPaging: () -> Unit,
    isPaging: Boolean,
    enabled: Boolean
): PaginateState {

    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(
        this,
        enabled,
    ) {

        if (enabled) {
            snapshotFlow { didReachEnd }.collect { isEnd ->
                if (isEnd) {
                    job?.cancel()
                    job = scope.launch {
                        onPaging()
                    }
                }
            }

        }

    }
    return remember {
        PaginateState(
            isPaging = isPaging,
            onPaging = onPaging,
            enabled = enabled
        )
    }
}

@Composable
fun AppScrollable.pullRefreshState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    keepIndicatorMillis: Long,
    shouldShowIndicator: Boolean,
    isIndicatorVisible: MutableState<Boolean>
): PullRefreshState {

    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(
        remember { derivedStateOf { this.firstVisibleIndex } },
        this.isScrolling,
        refreshing
    ) {
        if (this@pullRefreshState.firstVisibleIndex == 0 &&
            this@pullRefreshState.firstVisibleOffset == 0 &&
            this@pullRefreshState.isScrolling &&
            !refreshing
        ) {
            job?.cancel()
            if (shouldShowIndicator) {
                isIndicatorVisible.value = true
            }

            job = scope.launch {
                onRefresh()
                if (shouldShowIndicator) {
                    delay(keepIndicatorMillis)
                    isIndicatorVisible.value = false
                }
            }
        }
    }
    return remember {
        PullRefreshState(
            shouldShowIndicator = shouldShowIndicator,
            keepIndicatorMillis = keepIndicatorMillis,
            refreshing = refreshing,
            onRefresh = onRefresh,
        )
    }
}
