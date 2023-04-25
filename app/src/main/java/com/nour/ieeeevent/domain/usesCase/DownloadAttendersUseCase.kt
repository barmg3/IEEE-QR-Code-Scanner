package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.data.retrofit.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DownloadAttendersUseCase @Inject constructor(private val repository: AttenderRepositoryImpl) {

     operator fun invoke() =
        flow {
            val result = repository.getAttendersFromSheet()
            if (result.isSuccessful) {
                emit(State.Success(result.body()!!))
            } else
                emit(State.Failure("error: " + result.message()))

        }.catch { emit(State.Failure("error : " + it.message)) }


}