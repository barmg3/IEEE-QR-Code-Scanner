package com.nour.ieeeevent.ui.home


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nour.ieeeevent.data.Repository
import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.util.Constants.Image_STATE_NOT_FOUND
import com.nour.ieeeevent.util.Constants.Image_STATE_NOT_VIB
import com.nour.ieeeevent.util.Constants.Image_STATE_VIB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (var repository: Repository) : ViewModel(){

    val name = MutableLiveData<String?>()
    var imageState = MutableLiveData<Int?>()


    @SuppressLint("SuspiciousIndentation")
    fun getAttenderFromDB(id: Int ) {
        viewModelScope.launch (Dispatchers.IO){
       val sheetAttender = repository.getAttender(id)
            if(sheetAttender==null)
                  setImageNotFoundInUi()
            else{
                    upDateUi(sheetAttender)
                repository.upDateAttenderCallInSheet("A${id}")
        }}
    }

    private fun setImageNotFoundInUi() {
        imageState.postValue(Image_STATE_NOT_FOUND)
    }

    private fun upDateUi(attender : Attender) {
        name.postValue(attender.name)
        val state = if (attender.VIP.toInt() == Image_STATE_VIB ) Image_STATE_VIB
                            else  Image_STATE_NOT_VIB
        imageState.postValue(state)
    }

    fun clearDataFromUi(){
        name .postValue("")
        val clearNumber = -2
        imageState.postValue(clearNumber)
    }





}