package com.nour.ieeeevent.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nour.ieeeevent.data.modeles.AttenderEntity

@Database( entities = [AttenderEntity::class], version = 9)
 abstract class RoomDB : RoomDatabase() {
    abstract fun getDio() :DataAccessObject
}