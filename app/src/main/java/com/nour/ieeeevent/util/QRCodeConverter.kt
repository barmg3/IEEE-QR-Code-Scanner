package com.nour.ieeeevent.util

import android.util.Log
import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.util.Constants.QR_CODE_ID_INDEX
import com.nour.ieeeevent.util.Constants.QR_CODE_NAME_INDEX
import com.nour.ieeeevent.util.Constants.QR_CODE_VIB_INDEX

object QRCodeConverter {


    fun convertQRCodeTextToAttender(text : String):Attender{
        val qrCodeInformation = text.split(":")
        val id = qrCodeInformation[QR_CODE_ID_INDEX].trim().toInt()
        val name = qrCodeInformation[QR_CODE_NAME_INDEX].trim().lowercase()
        val vib = qrCodeInformation[QR_CODE_VIB_INDEX].trim().lowercase()

        return Attender(id ,name ,vib)
    }


}