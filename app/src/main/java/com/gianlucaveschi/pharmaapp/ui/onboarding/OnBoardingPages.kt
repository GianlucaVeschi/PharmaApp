package com.gianlucaveschi.pharmaapp.ui.onboarding

import com.gianlucaveschi.pharmaapp.R

val onboardPages = listOf(
    Page(
        title = "Welcome in the Pharma App!",
        description = "This is a the very first intro screen of a very cool app",
        image = R.drawable.ic_android_black
    ),
    Page(
        title = "What's your name?",
        description = null,
        shouldShowInputField = true,
        image = R.drawable.ic_android_red
    ),
    Page(
        title = "Are you fine with analytics?",
        description = "Analytics data will be used only for internal purposes and is safely stored at any time",
        shouldShowToggleButton = true,
        image = R.drawable.ic_android_blue
    )
)