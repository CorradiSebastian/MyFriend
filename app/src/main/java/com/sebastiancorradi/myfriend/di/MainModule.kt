package com.sebastiancorradi.myfriend.di

import com.sebastiancorradi.myfriend.datasource.CatDataSource
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import com.sebastiancorradi.myfriend.datasource.repository.ICatRepository
import com.sebastiancorradi.myfriend.datasource.repository.db.CatDBRepository
import com.sebastiancorradi.myfriend.datasource.repository.db.ICatDBRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.CatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.local.ICatLocalRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.CatRemoteRepository
import com.sebastiancorradi.myfriend.datasource.repository.remote.ICatRemoteRepository
import com.sebastiancorradi.myfriend.domain.CatsRequestedUseCase
import com.sebastiancorradi.myfriend.domain.GetCatsUseCase
import com.sebastiancorradi.myfriend.domain.InitDetailsUseCase
import com.sebastiancorradi.myfriend.domain.SaveCatsUseCase
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
    fun providesCatDBRepository(): ICatDBRepository = CatDBRepository()

    @Provides
    fun provideSaveCatsUseCase(): SaveCatsUseCase = SaveCatsUseCase()
    @Provides
    fun providesCatDataSource(catRemoteRepository: ICatRemoteRepository,
                              catLocalRepository: ICatLocalRepository,
                              catDBRepository: ICatDBRepository,
                              saveCatsUseCase: SaveCatsUseCase): ICatDataSource =
    //fun providesCatDataSource(catRepository: ICatRemoteRepository,): ICatDataSource =
    //fun providesCatDataSource(catRepository: ICatLocalRepository,): ICatDataSource =
        CatDataSource(catRemoteRepository, catLocalRepository, catDBRepository, saveCatsUseCase)


    @Provides
    fun provideGetCatsUseCase(catDataSource: ICatDataSource): GetCatsUseCase =
        GetCatsUseCase(catDataSource)

    @Provides
    fun provideCatsRequestedUseCase(getCatsUseCase: GetCatsUseCase): CatsRequestedUseCase =
        CatsRequestedUseCase(getCatsUseCase)

    fun provideInitDetailScreen(): InitDetailsUseCase = InitDetailsUseCase()
}




