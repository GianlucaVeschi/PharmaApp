package com.gianlucaveschi.pharmaapp.ui.reminder

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gianlucaveschi.pharmaapp.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RemindersScreen(
    state: RemindersScreenState,
    viewModel: MainViewModel
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addMedication()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = LocalContentColor.current
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello ${state.userName}",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                if (state.medications.isEmpty()) {
                    EmptyMedicationsSection()
                } else {
                    Text(
                        text = "Here are your medications",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
                    LazyColumn {
                        items(
                            items = state.medications,
                            key = { it.id } // Needs to be provided when items in the data set change
                        ) { medication ->
                            val dismissState = rememberDismissState()

                            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                viewModel.removeMedication(medication.id)
                            }

                            SwipeToDismiss(
                                state = dismissState,
                                modifier = Modifier.padding(vertical = Dp(1f)),
                                directions = setOf(DismissDirection.EndToStart),
                                background = { SwipeToDismissBackground(dismissState) },
                                dismissContent = {
                                    MedicationCard(dismissState, medication)
                                }
                            )
                            Divider(Modifier.fillMaxWidth(), Color.DarkGray)
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun EmptyMedicationsSection() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Add your medications here to be reminded when you should take them",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MedicationCard(
    dismissState: DismissState,
    medication: Medication
) {
    Card(
        elevation = animateDpAsState(
            if (dismissState.dismissDirection != null) 4.dp else 0.dp
        ).value,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = medication.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = "Dosage: ${medication.dosage}")
            Text(text = "Frequency: ${medication.frequency}")
            Text(text = "Date: ${medication.date}")
            Text(text = "ID : ${medication.id}")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SwipeToDismissBackground(dismissState: DismissState) {
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.White
            DismissValue.DismissedToStart -> Color.Red
            DismissValue.DismissedToEnd -> Color.Red
        }
    )
    val alignment = Alignment.CenterEnd
    val icon = Icons.Default.Delete
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = Dp(20f)),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Delete Icon",
            modifier = Modifier.scale(scale)
        )
    }
}