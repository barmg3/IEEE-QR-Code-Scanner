package com.nour.ieeeevent.di

import android.content.Context
import androidx.room.Room
import com.nour.ieeeevent.data.room.DataAccessObject
import com.nour.ieeeevent.data.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDAO {

    @Singleton
    @Provides
    @Synchronized
    fun provideDataAccessObject(@ApplicationContext context : Context):  DataAccessObject{
        return Room.databaseBuilder(
            context.applicationContext ,
            RoomDB::class.java, "Attender.db"
        ).fallbackToDestructiveMigration().build().getDio()
    }
}