package com.adorastudios.crosswordsolver.ui.screenMain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adorastudios.crosswordsolver.domain.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
) : ViewModel() {
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

        val fileId = _state.value.language.fileId
        val letters = _state.value.letters

        viewModelScope.launch(Dispatchers.IO) {
            val words = wordsRepository.getWords(
                fileId = fileId,
                letters = letters,
            )
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(
                    words = if (words.isEmpty()) Words.Nothing else Words.Loaded(words),
                )
            }
        }
    }
}
