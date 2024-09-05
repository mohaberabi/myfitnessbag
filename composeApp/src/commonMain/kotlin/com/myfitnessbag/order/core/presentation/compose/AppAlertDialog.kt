package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.myfitnessbag.order.core.presentation.design.Spacing


@Composable
fun AppAlertDialog(
    modifier: Modifier = Modifier,
    positiveText: String = "",
    negativeText: String = "",
    isEmergent: Boolean = false,
    title: String = "",
    subtitle: String = "",
    onDismiss: () -> Unit = {},
    onPositive: () -> Unit = {},
    onNegative: () -> Unit = {},
) {

    AlertDialog(
        modifier = modifier,
        text = {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(Spacing.md),
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier =
                Modifier.fillMaxWidth(),
            ) {
                Text(

                    text = title, textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }

        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Text(
                modifier = Modifier
                    .clickable {
                        onPositive()
                    },
                text = positiveText,
                color = if (isEmergent) Color.Red else MaterialTheme.typography.bodyMedium.color
            )
        },
        dismissButton = {
            Text(
                text = negativeText,
                modifier = Modifier
                    .clickable {
                        onNegative()
                    },
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

        }
    )
}

