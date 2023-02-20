package com.nour.ieeeevent.data.room

import android.content.Context
import androidx.room.Room

object LocalDB {

    @Synchronized
    fun createRemindersDao( context: Context):  DAO{
        return Room.databaseBuilder(
            context.applicationContext ,
            RoomDB::class.java, "Attender.db"
        ).fallbackToDestructiveMigration().build().getDio()
    }

}