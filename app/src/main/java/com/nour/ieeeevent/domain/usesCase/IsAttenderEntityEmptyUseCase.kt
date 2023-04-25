package com.nour.ieeeevent.domain.usesCase

import com.nour.ieeeevent.data.AttenderRepositoryImpl
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class IsAttenderEntityEmptyUseCase @Inject constructor(private val repository: AttenderRepositoryImpl) {

   suspend  operator fun invoke() = repository.getAllAttenders().first().isNullOrEmpty()
}