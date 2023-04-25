package com.nour.ieeeevent.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.data.retrofit.AttenderAPI
import com.nour.ieeeevent.data.room.DataAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repositories {

    @Singleton
    @Provides
    fun provideRepository(
        dataAccessObject: DataAccessObject,
        attenderAPI : AttenderAPI,
        dataStore: DataStore<Preferences>
    ) = AttenderRepositoryImpl(dataAccessObject, attenderAPI,dataStore)

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) =
        context.createDataStore("data store")

}