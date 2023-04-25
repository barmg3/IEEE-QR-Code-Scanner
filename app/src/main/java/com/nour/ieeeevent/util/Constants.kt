package com.nour.ieeeevent.util

import androidx.datastore.preferences.core.preferencesKey


object Constants {
  const val BASE_URL = "https://script.google.com/macros/s/"
  const val SUPPLEMENT_URL = "/exec"
  val sheetNumberKey  =  preferencesKey<String>("sheetIndexKey")
  val apiKey = preferencesKey<String>("apiKey")


}