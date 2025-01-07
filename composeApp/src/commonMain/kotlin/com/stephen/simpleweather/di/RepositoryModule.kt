package com.stephen.simpleweather.di

import com.stephen.simpleweather.network.NetworkRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NetworkRepository> { NetworkRepository(get()) }
}