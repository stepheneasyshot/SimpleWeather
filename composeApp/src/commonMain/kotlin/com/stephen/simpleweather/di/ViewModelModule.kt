package com.stephen.simpleweather.di

import com.stephen.simpleweather.viewmodel.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}