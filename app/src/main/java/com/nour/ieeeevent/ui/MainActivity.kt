package com.nour.ieeeevent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.nour.ieeeevent.R
import com.nour.ieeeevent.data.Repository
import com.nour.ieeeevent.util.homeViewModel
import com.nour.ieeeevent.util.repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin()
        getAllAttenders()
        installSplashScreen()
        setContentView(R.layout.activity_main)
    }


    private fun startKoin() {
        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(homeViewModel, repository))
        }
    }

   fun getAllAttenders(){
       Repository(this.application).getAllAttender("1")
   }
}