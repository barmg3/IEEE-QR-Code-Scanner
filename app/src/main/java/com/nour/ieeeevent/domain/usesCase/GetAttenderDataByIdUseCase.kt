package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import javax.inject.Inject

class GetAttenderDataByIdUseCase @Inject constructor(private val repository: AttenderRepositoryImpl){

    operator fun invoke(id : Int?) =
        if (id==null) null else repository.getAttenderDataById(id)

}