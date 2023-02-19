package com.nour.ieeeevent.util

import com.nour.ieeeevent.data.Repository
import com.nour.ieeeevent.ui.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModel  = module {
    viewModel { HomeViewModel( repository = get()) }}

val repository = module {
    single {
        Repository(androidContext())
    }
}