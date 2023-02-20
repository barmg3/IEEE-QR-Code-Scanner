package com.nour.ieeeevent.util

import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.util.Constants.SHEET_ID_INDEX
import com.nour.ieeeevent.util.Constants.SHEET_NAME_INDEX
import com.nour.ieeeevent.util.Constants.SHEET_VIB_INDEX
import org.json.JSONObject
import java.util.stream.IntStream

object NetworkUtils {

    fun convertDataFromSheetToAttenders(text : String):List<Attender>?{
    return try {
         val json = JSONObject(text)
         val sheet =json.getJSONArray("values")
         val attenders =  ArrayList<Attender>()

         IntStream.range(0,sheet.length()).forEach{
             val raw = sheet.getJSONArray(it)
             val id = raw[SHEET_ID_INDEX].toString().trim().toInt()
             val name =raw[SHEET_NAME_INDEX].toString().trim().lowercase()
             val vib = raw[SHEET_VIB_INDEX].toString().trim().lowercase()

             val attender = Attender(id,name,vib)
             attenders.add(attender)
         }
          attenders
        } catch (e:Exception) { null }

    }

    fun responseRangeIsTrue(text : String):Boolean {
        return try {
            val json = JSONObject(text)
            val range = json.getString("range")
            range.toString().contains("A1:Z1001") || range.toString().contains("A1:Z1000")
        }catch (e:Exception){
            false }

    }


}