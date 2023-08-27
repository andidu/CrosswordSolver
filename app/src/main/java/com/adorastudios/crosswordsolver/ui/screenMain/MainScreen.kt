package com.adorastudios.crosswordsolver.ui.screenMain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adorastudios.crosswordsolver.R

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .statusBarsPadding()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            viewModel.availableLanguages.forEach { language: Language ->
                LanguageIconButton(
                    language = language,
                    selected = state.value.language == language,
                    onClick = {
                        viewModel.onEvent(MainEvent.LanguageClicked(language))
                    },
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(8.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {
                            viewModel.onEvent(MainEvent.LengthChanged(state.value.length - 1))
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Remove,
                                contentDescription = stringResource(
                                    id = R.string.contentDescription_minusOne,
                                ),
                            )
                        }
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = "${state.value.length}",
                            onValueChange = {
                                if (it.isEmpty()) {
                                    viewModel.onEvent(MainEvent.LengthChanged(0))
                                } else {
                                    it.toIntOrNull()?.let { newLength ->
                                        viewModel.onEvent(MainEvent.LengthChanged(newLength))
                                    }
                                }
                            },
                        )
                        IconButton(onClick = {
                            viewModel.onEvent(MainEvent.LengthChanged(state.value.length + 1))
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = stringResource(
                                    id = R.string.contentDescription_plusOne,
                                ),
                            )
                        }
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        itemsIndexed(
                            items = state.value.letters,
                        ) { index, c ->
                            OutlinedTextField(
                                modifier = Modifier.height(IntrinsicSize.Min).aspectRatio(1f),
                                singleLine = true,
                                value = "${c ?: ""}",
                                onValueChange = {
                                    viewModel.onEvent(
                                        MainEvent.LetterChanged(
                                            index,
                                            it.lastOrNull(),
                                        ),
                                    )
                                },
                                maxLines = 1,
                                label = {
                                    Text(text = "${index + 1}")
                                },
                            )
                        }
                    }
                }

                when (state.value.words) {
                    is Words.Loaded -> {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                        ) {
                        }
                    }

                    Words.Loading -> {}

                    Words.Nothing -> {}
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.2f),
                                Color.Transparent,
                            ),
                        ),
                        shape = RectangleShape,
                    ),
            )
        }
    }
}
