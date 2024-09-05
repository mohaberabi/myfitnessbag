package com.myfitnessbag.order.core.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.myfitnessbag.order.core.presentation.design.Spacing
import com.myfitnessbag.order.core.util.extensions.noRippleClickable


@Composable
fun ReadMoreText(
    text: String,
    modifier: Modifier = Modifier,
    min: Int = 4,
    style: TextStyle = LocalTextStyle.current,
    decoration: TextDecoration? = null,
    align: TextAlign? = null,
) {

    var showAll by remember { mutableStateOf(false) }
    val maxLines by derivedStateOf {
        if (showAll) Int.MAX_VALUE else min
    }
    val label by derivedStateOf {
        if (showAll) "Show less" else "Show More "
    }

    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text,
            textAlign = align,
            style = style,
            textDecoration = decoration,
            maxLines = maxLines,
        )
        Spacer(Modifier.height(Spacing.xs))
        if (text.trim().lines().size > min && text.isNotEmpty()) {
            Text(
                label,
                modifier = Modifier.noRippleClickable { showAll = !showAll },
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

    }


}