package com.gianlucaveschi.pharmaapp.ui.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PageUI(
    page: Page,
    onSaveNameButtonClicked: (String?) -> Unit,
    onAnalyticsToggleClicked: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = page.title,
            fontSize = 28.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        page.description?.let {
            Text(
                text = page.description,
                textAlign = TextAlign.Center, fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        if (page.shouldShowInputField) {
            var textField by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                value = textField,
                maxLines = 1,
                onValueChange = { newText ->
                    textField = newText
                },
            )
            Spacer(modifier = Modifier.height(12.dp))

            val context = LocalContext.current
            TextButton(
                onClick = {
                    Toast.makeText(context, "Name saved!", Toast.LENGTH_SHORT).show()
                    onSaveNameButtonClicked(textField.text)
                }
            ) {
                Text("Save")
            }
        }
        if (page.shouldShowToggleButton) {
            val context = LocalContext.current
            val checked = remember { mutableStateOf(false) }
            Switch(
                checked = checked.value,
                onCheckedChange = {
                    checked.value = it
                    Toast.makeText(context, getAnalyticsText(it), Toast.LENGTH_SHORT).show()
                    onAnalyticsToggleClicked(it)
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

fun getAnalyticsText(canTrack: Boolean): String {
    return if (canTrack)
        "Thanks, tracking will be very helpful."
    else
        "Got it, we won't track you."
}
