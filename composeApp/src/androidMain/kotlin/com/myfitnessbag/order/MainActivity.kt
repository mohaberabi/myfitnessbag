package com.myfitnessbag.order

import AppTheme
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.myfitnessbag.order.app.presentation.MyFitnessBagApp
import com.myfitnessbag.order.core.util.constants.AppShortcuts
import com.myfitnessbag.order.features.layout.domain.LayoutItem

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFitnessBagApp(
            )
        }
    }


}




