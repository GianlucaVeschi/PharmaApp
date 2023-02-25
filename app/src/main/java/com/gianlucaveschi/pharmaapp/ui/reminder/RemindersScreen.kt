package com.gianlucaveschi.pharmaapp.ui.reminder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    state: RemindersScreenState
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB click */ }
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
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add your medications here to be reminded when you should take them",
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        )
                    }
                } else {
                    Text(
                        text = "Here are your medications",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
                    LazyColumn {
                        items(state.medications) {
                            MedicationItem(medication = it)
                        }
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationItem(medication: Medication) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = medication.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Dosage: ${medication.dosage}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Frequency: ${medication.frequency}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: ${medication.date}")
        }
    }
}