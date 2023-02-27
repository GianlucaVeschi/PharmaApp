package com.gianlucaveschi.pharmaapp.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.pharmaapp.ui.reminder.Medication
import com.gianlucaveschi.pharmaapp.ui.reminder.RemindersScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPref: SharedPreferences
) : ViewModel() {

    private val _onboardingCompleted = MutableStateFlow(
        sharedPref.getBoolean("onboarding_completed", false)
    )
    val onboardingCompleted = _onboardingCompleted.asStateFlow()

    private val _remindersScreenState = MutableStateFlow(
        RemindersScreenState(
            userName = getUserName(),
            medications = listOf()
        )
    )
    val remindersScreenState = _remindersScreenState.asStateFlow()

    fun onGettingStartedClick() {
        sharedPref.edit().putBoolean("onboarding_completed", true).apply()
        _onboardingCompleted.value = sharedPref.getBoolean("onboarding_completed", false)
    }

    fun saveUserName(name: String) {
        sharedPref.edit().putString(USER_NAME_KEY, name).apply()
    }

    private fun getUserName(): String {
        return sharedPref.getString(USER_NAME_KEY, "") ?: ""
    }

    fun onAnalyticsToggleClicked(analyticsValue: Boolean) {
        sharedPref.edit().putBoolean("analytics", analyticsValue).apply()
    }

    fun addMedication() {
        val newMedications = _remindersScreenState.value.medications.toMutableList()
        newMedications.add(
            Medication(
                name = "MDMA",
                date = LocalDate.parse("2023-09-12"),
                dosage = "${Random.nextInt(10)}g",
                frequency = "Once per week"
            )
        )
        _remindersScreenState.value = _remindersScreenState.value.copy(
            medications = newMedications
        )
    }

    fun removeMedication(medication: Medication){
        val newMedications = _remindersScreenState.value.medications.toMutableList()
        Log.d("yao", "before: $newMedications")
        newMedications.remove(medication)
        Log.d("yao", "after: $newMedications")
        _remindersScreenState.value = _remindersScreenState.value.copy(
            medications = newMedications
        )
    }

    private companion object {
        const val USER_NAME_KEY = "user_name"
    }
}