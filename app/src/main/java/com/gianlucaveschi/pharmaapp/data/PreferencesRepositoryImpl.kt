package com.gianlucaveschi.pharmaapp.data

import android.content.SharedPreferences
import com.gianlucaveschi.pharmaapp.domain.repo.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
) : PreferencesRepository {

    override fun getUserName(): String {
        return sharedPrefs.getString(USER_NAME_KEY, "") ?: ""
    }

    override fun setUserName(userName: String) {
        sharedPrefs.edit().putString(USER_NAME_KEY, userName).apply()
    }

    override fun getOnboardingStatus(): Boolean {
        return sharedPrefs.getBoolean(ONBOARDING_KEY, false)
    }

    override fun setOnboardingStatus(onboardingComplete: Boolean) {
        sharedPrefs.edit().putBoolean(ONBOARDING_KEY, onboardingComplete).apply()
    }

    override fun setAnalyticsStatus(enableAnalytics: Boolean) {
        sharedPrefs.edit().putBoolean(ANALYTICS_KEY, enableAnalytics).apply()
    }

    private companion object {
        const val USER_NAME_KEY = "user_name"
        const val ONBOARDING_KEY = "onboarding_completed"
        const val ANALYTICS_KEY = "analytics"
    }
}