package com.nour.ieeeevent.ui


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.nour.ieeeevent.util.QRCodeConverter.convertQRCodeTextToAttender
import java.lang.Exception


class QRCodeScanner (private val context: Context,private val qrCodeResult: QRCodeResult) : OnSuccessListener<List<Barcode>> , OnFailureListener {

    private  var barCodeScannerOption : BarcodeScannerOptions
    private var barCodeScanner : BarcodeScanner



    init{
        barCodeScannerOption  =
            BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS).build()

        barCodeScanner = BarcodeScanning.getClient(barCodeScannerOption)
    }

    fun getTextQrCodes(uri :Uri) {
        getQrCodeResult(uri).addOnSuccessListener(this)
            .addOnFailureListener(this)
    }

    private fun getInputImage(url : Uri): InputImage = InputImage.fromFilePath(context,url)

    private fun getQrCodeResult(uri : Uri) : Task<List<Barcode>> {
        return  barCodeScanner.process(getInputImage(uri))
    }

    override fun onSuccess(barcodes: List<Barcode>?) {
        if(!barcodes.isNullOrEmpty())extractBarcodeInfo(barcodes)
    }

    override fun onFailure(p0: Exception) {
        Toast.makeText(context,p0.message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun extractBarcodeInfo(barcodes: List<Barcode>){
        for(barcode in barcodes){

          val text = barcode.rawValue
            Log.e("barcode",text.toString())
            if (!text.isNullOrEmpty())
                   sendBarCodeDetails(text)
            else Log.e("barcode","isnull"+text)
        }
    }

    private fun sendBarCodeDetails(text : String){
        val attender = convertQRCodeTextToAttender(text)
        qrCodeResult.getQRCodeResult(attender)
    }
}