package com.nour.ieeeevent.ui.sheetData


data class SheetDataUIState(
    var sheetNumber: String? = null,
    var apiKey: String? = null ,
    val isLoading:Boolean = false,
    val errorMessage: String? = null,
    val finishedSuccessfully :Boolean = false
)