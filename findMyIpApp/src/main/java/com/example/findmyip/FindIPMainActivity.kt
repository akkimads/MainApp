package com.example.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.findmyip.screens.UserListScreen
import com.example.findmyip.ui.theme.FindMyIPTheme
import com.example.findmyip.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindIPMainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIPTheme {
                UserListScreen(viewModel = viewModel)

            }
        }
    }
}