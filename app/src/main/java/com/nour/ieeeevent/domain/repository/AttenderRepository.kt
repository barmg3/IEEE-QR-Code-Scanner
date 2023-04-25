package com.nour.ieeeevent.domain.repository

import com.nour.ieeeevent.data.modeles.AttenderDto
import com.nour.ieeeevent.data.modeles.AttenderEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AttenderRepository {
    suspend fun getAttendersFromSheet(): Response<AttenderDto>
    suspend fun upDateAttenderBackgroundColor(rowNumber: String)
    suspend fun saveSheetData(sheetIndex: String, url: String)
    fun saveAttendersData(attenders: List<AttenderEntity>)
    fun getAttenderDataById(id: Int): AttenderEntity?
    fun deleteAllAttenders()
    fun getAllAttenders(): Flow<List<AttenderEntity>?>
}