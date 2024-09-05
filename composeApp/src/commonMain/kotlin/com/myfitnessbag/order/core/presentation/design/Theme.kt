import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import com.myfitnessbag.order.core.domain.model.AppLocales

import com.myfitnessbag.order.core.presentation.design.appTypography
import com.myfitnessbag.order.core.presentation.design.lightScheme


@Composable
fun AppTheme(
    local: String = AppLocales.En.code,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = lightScheme,
        typography = appTypography(lang = local),
        content = content
    )
}

