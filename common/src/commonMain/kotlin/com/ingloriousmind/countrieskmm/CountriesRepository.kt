package com.ingloriousmind.countrieskmm

import com.apollographql.apollo3.ApolloClient

interface CountriesRepositoryType {
    suspend fun fetchCountries(): List<CountriesQuery.Country>
}

class CountriesRepository(private val client: ApolloClient) : CountriesRepositoryType {
    override suspend fun fetchCountries(): List<CountriesQuery.Country> {
        val response = client.query(CountriesQuery()).execute()

        return response.data?.countries.orEmpty()
    }
}
