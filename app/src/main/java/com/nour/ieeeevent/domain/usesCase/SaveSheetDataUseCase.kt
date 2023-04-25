package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.data.retrofit.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveSheetDataUseCase @Inject constructor(private val repository: AttenderRepositoryImpl) {

    operator fun invoke(sheetIndex :String?, url : String?): Flow<State<String?>> {
        return flow {
            if( !(sheetIndex.isNullOrEmpty()||url.isNullOrEmpty()) ){
                repository.saveSheetData(sheetIndex,url)
            emit(State.Success(null))}
            else emit(State.Failure("there are empty field"))
        }

    }
}