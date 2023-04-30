package com.ingloriousmind.countrieskmm.di

import com.apollographql.apollo3.ApolloClient
import com.ingloriousmind.countrieskmm.CountriesRepository
import com.ingloriousmind.countrieskmm.CountriesRepositoryType
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication = startKoin {
    modules(
        appModule,
        commonModule,
        platformModule
    )
}

expect val platformModule: Module

val commonModule = module {
    factory {
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    factory { CountriesRepository(get()) }
    factory<CountriesRepositoryType> { get<CountriesRepository>() }
}
