package com.adorastudios.crosswordsolver.ui.screenMain

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.adorastudios.crosswordsolver.R

@Composable
fun WordTile(
    modifier: Modifier = Modifier,
    word: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current

        Text(text = word)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                clipboardManager.setText(AnnotatedString(word))
            },
        ) {
            Icon(
                imageVector = Icons.Rounded.ContentCopy,
                contentDescription = stringResource(id = R.string.contentDescription_copy),
            )
        }
    }
}
