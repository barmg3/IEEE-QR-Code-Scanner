package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import com.nour.ieeeevent.data.modeles.AttenderDto
import com.nour.ieeeevent.data.modeles.AttenderEntity
import com.nour.ieeeevent.data.retrofit.State
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAttendersInLocalDBUseCase @Inject constructor(private val repository: AttenderRepositoryImpl) {

    suspend operator fun invoke(attender: AttenderDto) =
        flow<State<String>> {
            try {
                if (attender.attenders.isNotEmpty()) {
                    val attendersDTO = attender.attenders.map {
                        AttenderEntity(
                            it[0].toString()[0].digitToInt(),
                            it[1].toString(),
                            it[2].toString()
                        )
                    }
                    repository.saveAttendersData(attendersDTO)
                    emit(State.Success(null))
                } else emit(State.Failure("sheet is empty"))
            } catch (e: Exception) {
                emit(State.Failure("error"+e.message))
            }
        }
}