package com.ilyakoz.vknewsapp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyakoz.vknewsapp.R
import com.ilyakoz.vknewsapp.ui.theme.Black500
import com.ilyakoz.vknewsapp.ui.theme.Black900
import com.ilyakoz.vknewsapp.ui.theme.Violet

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black500),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.ic_vk),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(100.dp))
            ElevatedButton(
                onClick = { onLoginClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black900,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.button_login))

            }

        }


    }

}