package com.nour.ieeeevent.util

import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.util.Constants.QR_CODE_ID_INDEX
import com.nour.ieeeevent.util.Constants.QR_CODE_NAME_INDEX
import com.nour.ieeeevent.util.Constants.QR_CODE_VIB_INDEX

object QRCodeConverter {


    fun getIdFromQrCodeText(text : String):Int{
        val qrCodeInformation = text.split(":")
        val id = qrCodeInformation[QR_CODE_ID_INDEX].trim().toInt()
         return id
    }


}