package com.nour.ieeeevent.ui.home

import androidx.lifecycle.*
import com.nour.ieeeevent.data.retrofit.State
import com.nour.ieeeevent.domain.usesCase.DeleteAllAttendersUseCase
import com.nour.ieeeevent.domain.usesCase.GetAttenderDataByIdUseCase
import com.nour.ieeeevent.domain.usesCase.UpDateAttenderBackgroundColorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAttenderDataByIdUseCase: GetAttenderDataByIdUseCase,
    private val upDateAttenderBackgroundColorUseCase: UpDateAttenderBackgroundColorUseCase,
    private val deleteAllAttendersUseCase: DeleteAllAttendersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(HomeUiState())
    val uiState: LiveData<HomeUiState> = _uiState

    fun getAttenderDataById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val attender = getAttenderDataByIdUseCase(id)
            if (attender != null) {
                _uiState.postValue(_uiState.value!!.copy(attender = attender))
                upDateAttenderBackgroundColor(id)
            } else _uiState.postValue(_uiState.value!!.copy(errorMessage = "No found attender with this Id"))
        }
    }

    private suspend fun upDateAttenderBackgroundColor(id: Int) {
        upDateAttenderBackgroundColorUseCase(id).last().let { result ->
            if (result is State.Failure)
                _uiState.postValue(_uiState.value!!.copy(errorMessage = result.message))
        }
    }

    fun deleteAllAttenders() = viewModelScope.launch(Dispatchers.IO) { deleteAllAttendersUseCase() }

    fun clearData() = _uiState.postValue(HomeUiState())

}