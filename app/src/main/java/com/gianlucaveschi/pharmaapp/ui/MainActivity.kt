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
import com.gianlucaveschi.pharmaapp.ui.reminder.RemindersScreen
import com.gianlucaveschi.pharmaapp.ui.reminder.Medication
import com.gianlucaveschi.pharmaapp.ui.reminder.RemindersScreenState
import com.gianlucaveschi.pharmaapp.ui.onboarding.OnboardingScreen
import com.gianlucaveschi.pharmaapp.ui.theme.PharmaAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

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
                        viewModel.onboardingCompleted.collectAsState()

                    if (!onboardingCompleted.value) {
                        OnboardingScreen(
                            viewModel = viewModel
                        )
                    } else {
                        val userName = viewModel.getUserName()
                        RemindersScreen(
                            state = RemindersScreenState(
                                userName = userName,
                                medications = listOf(
                                    generateRandomMedication(),
                                    generateRandomMedication(),
                                    generateRandomMedication()
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}


// Define a list of possible medication names
val medicationNames = listOf("Aspirin", "Ibuprofen", "Acetaminophen", "Naproxen", "Amoxicillin")

// Define a function to generate a random medication
fun generateRandomMedication(): Medication {
    val randomName = medicationNames[Random.nextInt(medicationNames.size)]
    val randomDosage = "${Random.nextInt(5, 20)}mg"
    val randomFrequency = "Once per day"
    return Medication(
        name = randomName,
        date = LocalDate.parse("2023-09-12"),
        dosage = randomDosage,
        frequency = randomFrequency
    )
}