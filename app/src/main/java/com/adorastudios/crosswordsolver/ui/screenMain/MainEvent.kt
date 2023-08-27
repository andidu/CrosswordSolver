package com.adorastudios.crosswordsolver.ui.screenMain

sealed class MainEvent {
    data class LanguageClicked(val language: Language) : MainEvent()
    data class LengthChanged(val length: Int) : MainEvent()
    data class LetterChanged(val index: Int, val letter: Char?) : MainEvent()
}
