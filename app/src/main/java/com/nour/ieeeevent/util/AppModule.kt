package com.nour.ieeeevent.util

import com.nour.ieeeevent.data.Repository
import com.nour.ieeeevent.data.room.LocalDB
import com.nour.ieeeevent.ui.home.HomeViewModel
import com.nour.ieeeevent.ui.sheetName.SheetNameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModel  = module {
    viewModel { HomeViewModel( repository = get()) }}

val sheetNameViewModel  = module {
    viewModel { SheetNameViewModel( repository = get()) }}

val repository = module {
    single {
        Repository(LocalDB.createRemindersDao( androidContext() ))
    }
}

