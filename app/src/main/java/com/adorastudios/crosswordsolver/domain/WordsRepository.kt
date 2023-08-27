package com.adorastudios.crosswordsolver.domain

interface WordsRepository {
    fun getWords(fileId: Int, letters: List<Char?>): List<String>
}
