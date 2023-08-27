package com.adorastudios.crosswordsolver.ui.screenMain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableState<MainState> = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val availableLanguages: List<Language> = listOf(
        Language.English,
        Language.Spanish,
        Language.French,
        Language.German,
    )

    init {
        loadWordsByConstrains()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.LanguageClicked -> {
                _state.value = state.value.copy(
                    language = event.language,
                )
                loadWordsByConstrains()
            }

            is MainEvent.LengthChanged -> {
                var list = state.value.letters.toMutableList()
                if (event.length > list.size) {
                    list.addAll(List(event.length - list.size) { null })
                } else if (event.length < list.size) {
                    list = list.subList(0, event.length)
                }
                _state.value = state.value.copy(
                    length = event.length,
                    letters = list.toList(),
                )

                loadWordsByConstrains()
            }

            is MainEvent.LetterChanged -> {
                val list = state.value.letters.toMutableList()
                list[event.index] = event.letter
                _state.value = state.value.copy(
                    letters = list,
                )

                loadWordsByConstrains()
            }
        }
    }

    private fun loadWordsByConstrains() {
        _state.value = state.value.copy(
            words = Words.Loading,
        )
    }
}
