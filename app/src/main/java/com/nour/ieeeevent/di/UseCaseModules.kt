package com.nour.ieeeevent.di

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.domain.usesCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModules {


    @Provides
    fun provideGetAttendersUseCase(repository: AttenderRepositoryImpl) = DownloadAttendersUseCase(repository)


    @Provides
    fun provideSaveAttendersInLocalDBUseCase(repository: AttenderRepositoryImpl) = SaveAttendersInLocalDBUseCase(repository)


    @Provides
    fun provideSaveSheetDataUseCase(repository: AttenderRepositoryImpl) = SaveSheetDataUseCase(repository)

    @Provides
    fun providesGetAttenderDataById(repository: AttenderRepositoryImpl) = GetAttenderDataByIdUseCase(repository)

    @Provides
    fun providesIsAttenderEntityEmptyUseCase(repository: AttenderRepositoryImpl) = IsAttenderEntityEmptyUseCase(repository)

    @Provides
    fun providesUpDateAttenderBackgroundColorUseCase(repository: AttenderRepositoryImpl) = UpDateAttenderBackgroundColorUseCase(repository)

    @Provides
    fun providesDeleteAllAttendersUseCase(repository: AttenderRepositoryImpl) = DeleteAllAttendersUseCase(repository)
}