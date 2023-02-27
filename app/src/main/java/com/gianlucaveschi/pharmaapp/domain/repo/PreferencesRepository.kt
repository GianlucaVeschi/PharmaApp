package com.gianlucaveschi.pharmaapp.domain.repo

interface PreferencesRepository {
    fun getUserName(): String
    fun setUserName(userName: String)
    fun getOnboardingStatus(): Boolean
    fun setOnboardingStatus(onboardingComplete: Boolean)
    fun setAnalyticsStatus(enableAnalytics: Boolean)
}
