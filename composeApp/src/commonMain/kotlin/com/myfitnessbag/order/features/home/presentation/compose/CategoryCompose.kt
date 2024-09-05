package com.myfitnessbag.order.features.home.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myfitnessbag.order.core.presentation.compose.CachedImage
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.presentation.compose.ext.shimmerEffect
import com.myfitnessbag.order.core.util.extensions.noRippleClickable


@Composable
fun CategoryCompose(
    modifier: Modifier = Modifier,
    category: CategoryModel,
    onClick: () -> Unit,
) {


    Column(
        modifier = modifier
            .noRippleClickable { onClick() }
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(Spacing.sm))
        CachedImage(
            model = category.image ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.xs),
        )
        Text(
            category.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )


    }

}

@Composable
fun CategoryHomeShimmer(modifier: Modifier = Modifier) {
    Box(modifier.shimmerEffect().size(80.dp))
}