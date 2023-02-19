package com.nour.ieeeevent.data

import android.content.Context
import android.util.Log
import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.data.room.DAO
import com.nour.ieeeevent.data.room.LocalDB
import com.nour.ieeeevent.util.Constants
import com.nour.ieeeevent.util.Constants.GETURL
import com.nour.ieeeevent.util.Constants.POST_URL
import com.nour.ieeeevent.util.NetworkUtils.convertDataFromSheetToAttenders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

class Repository(var context: Context) {

   var  dao : DAO = LocalDB.createRemindersDao(context)

   private val okHttpClient = OkHttpClient.Builder().apply {
      callTimeout(60000, TimeUnit.MILLISECONDS)
      readTimeout(60000, TimeUnit.MILLISECONDS)
   }.build()



   fun getAttender(id : Int): Attender?{
       return dao.getAttender(id)
   }

   fun getAllAttender(sheetName:String)  {
      val request = Request.Builder().url(GETURL(sheetName)).build()
      okHttpClient.newCall(request).enqueue(CallbackGet(dao))
   }

   fun upDateAttenderCallInSheet( cellName : String){
       val fromBody =FormBody.Builder().add("range",cellName).build()
      val request = Request.Builder().post(fromBody).url(POST_URL).build()
      GlobalScope.launch(Dispatchers.IO) {
      okHttpClient.newCall(request).execute()}
   }


   private class CallbackGet(var dio: DAO):Callback{

      override fun onFailure(call: Call, e: IOException) {

      }

      override fun onResponse(call: Call, response: Response) {
         val sheet = response.body()?.string()
         if (!sheet.isNullOrEmpty()){
             val attenders = convertDataFromSheetToAttenders(sheet)
             dio.setAllAttenders(attenders)}
      }

   }




}