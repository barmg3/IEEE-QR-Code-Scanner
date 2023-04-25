package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.data.retrofit.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpDateAttenderBackgroundColorUseCase @Inject constructor(private val repository: AttenderRepositoryImpl) {

    suspend operator fun invoke(rowNumber : Int) = flow<State<String>> {
        repository.upDateAttenderBackgroundColor(rowNumber.toString())
        emit(State.Success(null))
    }.catch { emit(State.Failure(it.message)) }



}