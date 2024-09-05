package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


@Composable
fun <T> EventCollector(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: suspend CoroutineScope.(T) -> Unit,
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle, key1, key2) {

        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect { t ->
                    onEvent(this, t)
                }
            }
        }
    }
}