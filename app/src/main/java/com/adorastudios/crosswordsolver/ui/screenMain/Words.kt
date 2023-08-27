package com.adorastudios.crosswordsolver.ui.screenMain

sealed class Words {
    object Loading : Words()
    object Nothing : Words()
    data class Loaded(val list: List<String>) : Words()
}
