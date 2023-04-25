package com.nour.ieeeevent.data.room

import androidx.room.*
import com.nour.ieeeevent.data.modeles.AttenderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllAttenders(attenderEntities: List<AttenderEntity>)

    @Query("SELECT * FROM AttenderEntity WHERE id = :id")
    fun getAttender(id : Int): AttenderEntity?

    @Query("SELECT * FROM AttenderEntity")
    fun getAllAttenders(): Flow<List<AttenderEntity>?>

    @Query("DELETE FROM AttenderEntity")
    fun deleteAllAttenders()

}