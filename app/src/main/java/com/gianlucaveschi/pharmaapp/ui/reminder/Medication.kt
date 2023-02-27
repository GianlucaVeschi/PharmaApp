package com.gianlucaveschi.pharmaapp.ui.reminder

import java.time.LocalDate

data class Medication(
    val id: Int,
    val name: String,
    val date: LocalDate,
    val dosage: String,
    val frequency: String
)
