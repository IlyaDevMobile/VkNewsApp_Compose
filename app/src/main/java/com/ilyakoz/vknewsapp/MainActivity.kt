package com.ilyakoz.vknewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilyakoz.vknewsapp.ui.theme.VkNewsAppTheme
import com.ilyakoz.vknewsapp.ui.theme.MainScreen

class MainActivity : ComponentActivity() {


    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsAppTheme {
                MainScreen(viewModel)
            }
        }
    }
}

