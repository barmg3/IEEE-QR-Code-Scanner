package com.nour.ieeeevent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nour.ieeeevent.R
import com.nour.ieeeevent.data.Repository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteAllData()
    }

    private fun deleteAllData() {
        val repository : Repository by inject()
        repository.deleteAllData()
    }



}