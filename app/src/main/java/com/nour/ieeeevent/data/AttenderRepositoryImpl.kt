package com.nour.ieeeevent.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.nour.ieeeevent.data.modeles.AttenderEntity
import com.nour.ieeeevent.data.retrofit.AttenderAPI
import com.nour.ieeeevent.data.room.DataAccessObject
import com.nour.ieeeevent.domain.repository.AttenderRepository
import com.nour.ieeeevent.util.Constants.SUPPLEMENT_URL
import com.nour.ieeeevent.util.Constants.apiKey
import com.nour.ieeeevent.util.Constants.sheetNumberKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class AttenderRepositoryImpl @Inject constructor(
    private val dataAccessObject: DataAccessObject,
    private val attenderAPI: AttenderAPI,
    private val dataStore: DataStore<Preferences>
) : AttenderRepository {


    override suspend fun getAttendersFromSheet() =
        attenderAPI.getAttenders(
            dataStore.data.first()[apiKey] + SUPPLEMENT_URL,
            dataStore.data.first()[sheetNumberKey]!!
        )

    override suspend fun upDateAttenderBackgroundColor(rowNumber: String) =
        attenderAPI.upDateAttenderBackgroundColor(
            dataStore.data.first()[apiKey] + SUPPLEMENT_URL,
            dataStore.data.first()[sheetNumberKey]!!,
            rowNumber
        )


    override suspend fun saveSheetData(sheetIndex: String, url: String) {
        dataStore.edit {
            it[sheetNumberKey] = sheetIndex
            it[apiKey] = url
        }
    }

    override fun saveAttendersData(attenders: List<AttenderEntity>) {
        dataAccessObject.setAllAttenders(attenders)
    }

    override fun getAttenderDataById(id: Int) = dataAccessObject.getAttender(id)

    override fun getAllAttenders() = dataAccessObject.getAllAttenders()

    override fun deleteAllAttenders() = dataAccessObject.deleteAllAttenders()
}