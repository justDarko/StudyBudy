package com.solo.studybudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.studybudy.navigation.AppNavigator
import com.solo.studybudy.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val isUserLoggedIn = viewModel.isUserLoggedIn.collectAsState()

            AppTheme(darkTheme = false) {
                BackgroundTheme()
                isUserLoggedIn.value?.let { userLoggedIn -> AppNavigator(isUserLoggedIn = userLoggedIn) }
            }
        }
    }
}