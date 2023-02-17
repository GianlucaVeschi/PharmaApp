package com.gianlucaveschi.pharmaapp.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPref: SharedPreferences
) : ViewModel() {

    private val _onboardingCompleted = MutableStateFlow(
        sharedPref.getBoolean("onboarding_completed", false)
    )
    val onboardingCompleted = _onboardingCompleted.asStateFlow()

    fun onGettingStartedClick() {
        sharedPref.edit().putBoolean("onboarding_completed", true).apply()
        _onboardingCompleted.value = sharedPref.getBoolean("onboarding_completed", false)
    }
}