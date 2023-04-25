package com.nour.ieeeevent.ui

import androidx.lifecycle.ViewModel
import com.nour.ieeeevent.domain.usesCase.IsAttenderEntityEmptyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isAttenderEntityEmptyUseCase: IsAttenderEntityEmptyUseCase):ViewModel() {

     var isAttenderEntityEmpty : Boolean = runBlocking { isAttenderEntityEmptyUseCase()}
}