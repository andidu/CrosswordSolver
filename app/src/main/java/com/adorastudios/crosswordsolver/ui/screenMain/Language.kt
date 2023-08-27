package com.adorastudios.crosswordsolver.ui.screenMain

import com.adorastudios.crosswordsolver.R

sealed class Language(
    val iconId: Int,
    val nameId: Int,
) {
    object English : Language(
        iconId = R.drawable.uk,
        nameId = R.string.language_UK,
    )

    object Spanish : Language(
        iconId = R.drawable.es,
        nameId = R.string.language_ES,
    )

    object French : Language(
        iconId = R.drawable.fr,
        nameId = R.string.language_FR,
    )

    object German : Language(
        iconId = R.drawable.de,
        nameId = R.string.language_DE,
    )
}
