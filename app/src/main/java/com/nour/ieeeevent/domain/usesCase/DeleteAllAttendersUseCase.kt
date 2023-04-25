package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import javax.inject.Inject

class DeleteAllAttendersUseCase @Inject constructor(private val repository: AttenderRepositoryImpl){

     operator fun invoke() = repository.deleteAllAttenders()
}