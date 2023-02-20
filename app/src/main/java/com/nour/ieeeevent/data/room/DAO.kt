package com.nour.ieeeevent.data.room

import androidx.room.*
import com.nour.ieeeevent.data.modeles.Attender

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllAttenders(attenders: List<Attender>)

    @Query("SELECT * FROM Attender WHERE id = :id")
    fun getAttender(id : Int): Attender?

    @Query("DELETE FROM Attender")
    fun deleteAllData()

}