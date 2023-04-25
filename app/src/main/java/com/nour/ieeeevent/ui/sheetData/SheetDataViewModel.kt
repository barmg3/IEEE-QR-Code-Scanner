package com.nour.ieeeevent.ui.sheetData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nour.ieeeevent.data.modeles.AttenderDto
import com.nour.ieeeevent.data.retrofit.State
import com.nour.ieeeevent.domain.usesCase.DownloadAttendersUseCase
import com.nour.ieeeevent.domain.usesCase.SaveAttendersInLocalDBUseCase
import com.nour.ieeeevent.domain.usesCase.SaveSheetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetDataViewModel @Inject constructor(
    private val saveSheetDataUseCase: SaveSheetDataUseCase,
    private val downloadAttendersUseCase: DownloadAttendersUseCase,
    private val saveAttendersInLocalDBUseCase: SaveAttendersInLocalDBUseCase,
) : ViewModel() {


    private var _uiState = MutableStateFlow(SheetDataUIState())
    val uiState: StateFlow<SheetDataUIState> = _uiState

    fun saveSheetData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(errorMessage = null, isLoading = true) }
            saveSheetDataUseCase(_uiState.value.sheetNumber, _uiState.value.apiKey).collect {
                if (it is State.Failure)
                    onFailure(it.message!!)
                else downloadAllAttenders()
            }
        }

    }

    private suspend fun downloadAllAttenders() {
        downloadAttendersUseCase().collect {
            if (it is State.Failure)
                onFailure(it.message!!)
            else if (it is State.Success) saveAttender(it.data!!)
        }
    }

    private suspend fun saveAttender(attender: AttenderDto) {
        saveAttendersInLocalDBUseCase(attender).collect {
            if (it is State.Failure)
                onFailure(it.message!!)
            else if (it is State.Success)
                onSaveAttenderSuccess()
        }
    }

    private fun onFailure(errorMessage: String) {
        _uiState.update { it.copy(isLoading = false, errorMessage = errorMessage) }
    }

    private fun onSaveAttenderSuccess() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                isLoading = false,
                finishedSuccessfully = true
            )
        }
    }
}