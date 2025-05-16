package com.example.mvicompose.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GreetingViewModel: ViewModel() {

    private val _state = MutableStateFlow(GreetingState())
    val state : StateFlow<GreetingState> = _state

    fun processIntent(intent: GreetingIntent){
        when (intent) {
            is GreetingIntent.EnterName -> _state.update { it.copy(name = intent.name) }

            is GreetingIntent.Greet -> _state.update { it.copy(showGreeting = true) }
        }
    }
}

data class GreetingState(
    val name: String = "",
    val showGreeting: Boolean = false
)

sealed class GreetingIntent {
    data class EnterName(val name: String): GreetingIntent()
    data object Greet: GreetingIntent()
}