package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.myfitnessbag.order.core.presentation.design.Spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun <T> CarouselSlider(
    modifier: Modifier = Modifier,
    items: List<T>,
    onItemClick: (T) -> Unit = {},
    key: ((T) -> Any)? = null,
    content: @Composable (T) -> Unit,
) {


    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { items.size }
    )

    val interval by remember {
        mutableIntStateOf(3000)
    }
    LaunchedEffect(
        key1 = true,
    ) {

        while (true) {

            delay(interval.toLong())
            scope.launch {
                if (pagerState.canScrollForward) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    pagerState.animateScrollToPage(0)
                }
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        HorizontalPager(
            state = pagerState,
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = Spacing.sm),
            key = {
                items[it].toString()
            }
        ) {
            val currentItem = items[pagerState.currentPage]
            content(currentItem)
        }

        Spacer(modifier = Modifier.height(Spacing.xs))
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            repeat(items.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Spacing.xs)
                        .clip(CircleShape)
                        .size(Spacing.sm)
                        .background(
                            if (pagerState.currentPage == index)
                                MaterialTheme.colorScheme.primary else Color.Gray
                        )
                )
            }
        }
    }
}
