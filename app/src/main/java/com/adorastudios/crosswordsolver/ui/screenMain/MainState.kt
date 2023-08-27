package com.adorastudios.crosswordsolver.ui.screenMain

data class MainState(
    val language: Language = Language.English,
    val length: Int = 5,
    val letters: List<Char?> = List(5) { null },
    val words: Words = Words.Loading,
)
