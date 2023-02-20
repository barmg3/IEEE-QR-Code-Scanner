package com.nour.ieeeevent.ui

import android.app.Application
import com.nour.ieeeevent.util.homeViewModel
import com.nour.ieeeevent.util.repository
import com.nour.ieeeevent.util.sheetNameViewModel
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@MainApplication)
            modules(listOf(homeViewModel, repository, sheetNameViewModel))
        }
    }

}