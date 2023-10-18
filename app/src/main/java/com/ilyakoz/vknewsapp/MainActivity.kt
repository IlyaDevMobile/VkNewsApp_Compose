package com.ilyakoz.vknewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ilyakoz.vknewsapp.ui.theme.VkNewsAppTheme
import com.ilyakoz.vknewsapp.ui.MainScreen

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsAppTheme {
                MainScreen()
            }
        }
    }
}

