package com.adorastudios.crosswordsolver.data

import android.content.res.Resources
import com.adorastudios.crosswordsolver.domain.WordsRepository
import java.io.BufferedReader
import java.io.InputStreamReader

class WordsRepositoryImpl(private val resources: Resources) : WordsRepository {
    override fun getWords(fileId: Int, letters: List<Char?>): List<String> {
        val stream = resources.openRawResource(fileId)
        val bufferedReader = BufferedReader(InputStreamReader(stream))
        val lines = bufferedReader.readLines()

        val size = letters.size

        return lines.filter { it.length == size && checkMask(it, letters) }
    }

    private fun checkMask(it: String, letters: List<Char?>): Boolean {
        letters.forEachIndexed { index, c ->
            if (c != null && c != it[index]) return false
        }
        return true
    }
}
