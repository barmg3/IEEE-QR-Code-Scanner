package com.nour.ieeeevent.util



object Constants {
    const val QR_CODE_ID_INDEX = 1
    const val QR_CODE_NAME_INDEX = 3
    const val QR_CODE_VIB_INDEX = 5

    const val SHEET_ID_INDEX = 0
    const val SHEET_NAME_INDEX = 1
    const val SHEET_VIB_INDEX = 2

    const val Image_STATE_NOT_FOUND = -1
    const val Image_STATE_NOT_VIB = 0
    const val Image_STATE_VIB = 1


    //Todo we need to change this URL
    fun GETURL(sheetName : String):String=
        "https://sheets.googleapis.com/v4/spreadsheets/1pJEa0MOQk4bkI7Fk1uYuH_Js7vsb9OQrFvEXvewj9vQ/values/" +
               sheetName+"?key=AIzaSyBc2osfMMgaewqtuP3RuyERgWnrAvum83s"
    //Todo we need to change this URL
    const val POST_URL= "https://script.google.com/macros/s/AKfycbwrO-J1CXVQ7NkU1aLFtZN_Dde3Zq6_zbjYj7lMKCJSlSFOVcvCucOZbraRBtJXk1G2Ww/exec?"

}