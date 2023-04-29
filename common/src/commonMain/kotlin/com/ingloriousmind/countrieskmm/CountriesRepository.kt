package com.ingloriousmind.countrieskmm

import com.apollographql.apollo3.ApolloClient

interface CountriesRepositoryType

class CountriesRepository : CountriesRepositoryType {
    suspend fun fetchCountries(): List<CountriesQuery.Country> {
        val client = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()

        val response = client.query(CountriesQuery()).execute()

        return response.data?.countries.orEmpty()
    }
}
