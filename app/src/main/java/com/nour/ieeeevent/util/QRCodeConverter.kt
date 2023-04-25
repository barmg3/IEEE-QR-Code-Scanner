package com.nour.ieeeevent.util


import com.google.mlkit.vision.barcode.common.Barcode

object QRCodeConverter {

    fun Barcode.getId(): Int? {
        val id = this.rawValue
        return if (id.isNullOrEmpty()) null
        else {
            val regex = Regex("\\d+")
            val matchResult = regex.find(id)
            matchResult?.value?.toIntOrNull()
        }
    }
}
