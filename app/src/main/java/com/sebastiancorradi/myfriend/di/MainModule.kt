package com.sebastiancorradi.myfriend.di

import com.sebastiancorradi.myfriend.datasource.CatDataSource
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import com.sebastiancorradi.myfriend.datasource.repository.ICatRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.CatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.ICatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.CatRemoteRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.ICatRemoteRepository
import com.sebastiancorradi.myfriend.domain.CatsRequestedUseCase
import com.sebastiancorradi.myfriend.domain.GetCatsUseCase
import com.sebastiancorradi.myfriend.domain.InitDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesCatLocalRepository(): ICatLocalRepository = CatLocalRepository()

    @Provides
    fun providesCatRemoteRepository(): ICatRemoteRepository = CatRemoteRepository()

    @Provides
    fun providesCatDataSource(catRepository: ICatRemoteRepository,): ICatDataSource =
        CatDataSource(catRepository)


    @Provides
    fun provideGetCatsUseCase(catDataSource: ICatDataSource): GetCatsUseCase =
        GetCatsUseCase(catDataSource)

    @Provides
    fun provideCatsRequestedUseCase(getCatsUseCase: GetCatsUseCase): CatsRequestedUseCase =
        CatsRequestedUseCase(getCatsUseCase)

    fun provideInitDetailScreen(): InitDetailsUseCase = InitDetailsUseCase()
}




