package com.example.mvicompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvicompose.R
import com.example.mvicompose.viewmodel.GreetingIntent
import com.example.mvicompose.viewmodel.GreetingViewModel

@Composable
fun GreetingScreen(viewModel: GreetingViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.name,
            onValueChange = { viewModel.processIntent(GreetingIntent.EnterName(it)) },
            label = { Text(stringResource(id = R.string.enter_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.processIntent(GreetingIntent.Greet) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.greet_button))
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (state.showGreeting) {
            val message = if (state.name.isBlank()) {
                stringResource(id = R.string.empty_name_warning)
            } else {
                stringResource(id = R.string.greeting_message, state.name)
            }

            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

