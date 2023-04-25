package com.nour.ieeeevent.data.retrofit

sealed class State<out T>{
    data class Success<T>(val data : T?) : State<T>()
    data class Failure(val message : String?) : State<Nothing>()
}
