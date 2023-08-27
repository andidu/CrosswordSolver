package com.adorastudios.crosswordsolver

import android.app.Application
import com.adorastudios.crosswordsolver.data.WordsRepositoryImpl
import com.adorastudios.crosswordsolver.domain.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppHiltModule {
    @Provides
    @Singleton
    fun provideWordsRepository(app: Application): WordsRepository {
        return WordsRepositoryImpl(app.resources)
    }
}
