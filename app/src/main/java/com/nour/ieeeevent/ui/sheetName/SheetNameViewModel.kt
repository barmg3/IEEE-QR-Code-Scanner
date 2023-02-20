package com.nour.ieeeevent.ui.sheetName

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nour.ieeeevent.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SheetNameViewModel (val repository: Repository): ViewModel() {

    val sheetName = MutableLiveData<String>()
    val sheetNameIsTrue = MutableLiveData<Boolean>()

         @SuppressLint("SuspiciousIndentation")
         fun checkIfSheetNameTrue(){
             if(!sheetName.value.isNullOrEmpty())
                 checkIfSheetNameIsTrue()
         }

   private fun checkIfSheetNameIsTrue(){
       viewModelScope.launch (Dispatchers.IO){
     sheetNameIsTrue.postValue(  repository.checkIfSheetNameIsTrue(sheetName.value!!))
   }}
}