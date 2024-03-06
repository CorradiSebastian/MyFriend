package com.sebastiancorradi.myfriend.ui.di

import com.sebastiancorradi.myfriend.datasource.CatDataSource
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import com.sebastiancorradi.myfriend.datasource.ICatRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.CatLocalRepository
import com.sebastiancorradi.myfriend.domain.CatsRequestedUseCase
import com.sebastiancorradi.myfriend.domain.GetCatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesCatRepository(): ICatRepository = CatLocalRepository()

    @Provides
    fun providesCatDataSource(catRepository: ICatRepository): ICatDataSource =
        CatDataSource(catRepository)


    @Provides
    fun provideGetCatsUseCase(catDataSource: ICatDataSource): GetCatsUseCase =
        GetCatsUseCase(catDataSource)

    @Provides
    fun provideCatsRequestedUseCase(getCatsUseCase: GetCatsUseCase): CatsRequestedUseCase =
        CatsRequestedUseCase(getCatsUseCase)
}




