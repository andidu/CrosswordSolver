package com.adorastudios.crosswordsolver.ui.screenMain

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun LanguageIconButton(
    modifier: Modifier = Modifier,
    language: Language,
    selected: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        if (selected) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(
                        shape = CircleShape,
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                painter = painterResource(id = language.iconId),
                contentDescription = stringResource(id = language.nameId),
            )
        } else {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                painter = painterResource(id = language.iconId),
                contentDescription = stringResource(id = language.nameId),
            )
        }
    }
}
