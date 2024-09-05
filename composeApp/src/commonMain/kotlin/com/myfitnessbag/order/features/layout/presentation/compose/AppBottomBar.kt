package com.myfitnessbag.order.features.layout.presentation.compose

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import com.myfitnessbag.order.core.presentation.compose.BadgeButton
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    top: LayoutItem,
    onClick: (LayoutItem) -> Unit = {},
    cartCount: Int = 0
) {


    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {

        LayoutItem.entries.forEach { item ->

            NavigationBarItem(
                label = {
                    Text(
                        stringResource(item.label)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
                selected = top == item,
                onClick = { onClick(item) },
                icon = {
                    if (item == LayoutItem.Cart) {
                        BadgeButton(
                            showBadge = cartCount > 0,
                            badgeColor = Color.Red,
                            label = cartCount.toString(),
                            onClick = { onClick(item) }
                        )
                    } else {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = stringResource(item.label),
                            modifier = Modifier.size(24.dp)
                        )
                    }


                }
            )
        }
    }
}

