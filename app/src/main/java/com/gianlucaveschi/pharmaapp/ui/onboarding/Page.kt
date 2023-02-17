package com.gianlucaveschi.pharmaapp.ui.onboarding

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    val toggleButton : Boolean = false
)