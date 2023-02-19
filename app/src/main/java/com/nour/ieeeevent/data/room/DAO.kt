package com.nour.ieeeevent.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nour.ieeeevent.data.modeles.Attender

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllAttenders(attenders: List<Attender>)

    @Query("SELECT * FROM Attender WHERE id = :id")
    fun getAttender(id : Int): Attender?


}