package com.nour.ieeeevent.data.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.nour.ieeeevent.data.modeles.Attender

@Database( entities = [Attender::class], version = 5)
 abstract class RoomDB : RoomDatabase() {
    abstract fun getDio() :DAO
}