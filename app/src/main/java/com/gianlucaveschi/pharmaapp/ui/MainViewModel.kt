package com.gianlucaveschi.pharmaapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.pharmaapp.domain.repo.PreferencesRepository
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
    private val prefsRepo: PreferencesRepository
) : ViewModel() {

    private val _onboardingCompleted = MutableStateFlow(
        prefsRepo.getOnboardingStatus()
    )
    val onboardingCompleted = _onboardingCompleted.asStateFlow()

    private val _remindersScreenState = MutableStateFlow(
        RemindersScreenState(
            userName = prefsRepo.getUserName(),
            medications = listOf()
        )
    )
    val remindersScreenState = _remindersScreenState.asStateFlow()

    fun onGettingStartedClick() {
        prefsRepo.setOnboardingStatus(true)
        _onboardingCompleted.value = prefsRepo.getOnboardingStatus()
    }

    fun saveUserName(name: String) {
        prefsRepo.setUserName(name)
    }

    fun onAnalyticsToggleClicked(analyticsValue: Boolean) {
        prefsRepo.setAnalyticsStatus(analyticsValue)
    }

    fun addMedication() {
        val newMedications = _remindersScreenState.value.medications.toMutableList()
        newMedications.add(
            Medication(
                name = "MDMA",
                id = newMedications.size + 1,
                date = LocalDate.parse("2023-09-12"),
                dosage = "${Random.nextInt(10)}g",
                frequency = "Once per week"
            )
        )
        _remindersScreenState.value = _remindersScreenState.value.copy(
            medications = newMedications
        )
    }

    fun removeMedication(medication: Medication) {
        val newMedications = _remindersScreenState.value.medications.toMutableList()
        Log.d("yao", "before: $newMedications")
        newMedications.remove(medication)
        Log.d("yao", "after: $newMedications")
        _remindersScreenState.value = _remindersScreenState.value.copy(
            medications = newMedications
        )
    }
}