package com.gianlucaveschi.pharmaapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gianlucaveschi.pharmaapp.ui.onboarding.OnboardingScreen
import com.gianlucaveschi.pharmaapp.ui.reminder.RemindersScreen
import com.gianlucaveschi.pharmaapp.ui.reminder.RemindersScreenState
import com.gianlucaveschi.pharmaapp.ui.theme.PharmaAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagerApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PharmaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val onboardingCompleted: State<Boolean> =
                        mainViewModel.onboardingCompleted.collectAsState()

                    val remindersScreenState: State<RemindersScreenState> =
                        mainViewModel.remindersScreenState.collectAsState()

                    if (!onboardingCompleted.value) {
                        OnboardingScreen(
                            viewModel = mainViewModel
                        )
                    } else {
                        RemindersScreen(
                            state = remindersScreenState.value,
                            viewModel = mainViewModel
                        )
                    }
                }
            }
        }
    }
}