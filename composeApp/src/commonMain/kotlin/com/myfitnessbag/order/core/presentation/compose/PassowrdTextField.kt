package com.myfitnessbag.order.core.presentation.compose


import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.myfitnessbag.order.core.presentation.design.LockIcon
import com.myfitnessbag.order.core.presentation.design.LockOpenIcon

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onChange: (String) -> Unit = {},
    error: Boolean = false,
    errorText: String? = null,
) {

    var showPassword by remember {
        mutableStateOf(false)
    }


    var transformations =
        if (showPassword) VisualTransformation.None else PasswordVisualTransformation()

    PrimaryTextField(
        value = value,
        onChanged = onChange,
        errorText = errorText,
        error = error,
        modifier = modifier,
        label = "Password",
        visualTransformations = transformations,
        placeHolder = "********",
        suffix = {
            Icon(
                modifier = Modifier
                    .clickable {
                        showPassword = !showPassword
                    },
                imageVector = if (showPassword) LockIcon else LockOpenIcon,
                contentDescription = ""
            )
        }

    )
}

