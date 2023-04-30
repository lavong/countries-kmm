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

fun newCountry() = CountriesQuery.Country(
    code = "",
    name = "",
    native = "",
    phone = "",
    continent = CountriesQuery.Continent("", ""),
    currency = null,
    languages = emptyList(),
    emoji = "",
    emojiU = "",
)

fun demoCountries(): List<CountriesQuery.Country> = listOf(
    CountriesQuery.Country(
        code = "CH",
        name = "Switzerland",
        native = "Schweiz",
        phone = "41",
        continent = CountriesQuery.Continent(code = "EU", name = "Europe"),
        currency = "CHE,CHF,CHW",
        languages = listOf(
            CountriesQuery.Language(code = "de", name = "German", native = "Deutsch", rtl = false),
            CountriesQuery.Language(code = "fr", name = "French", native = "Français", rtl = false),
            CountriesQuery.Language(code = "it", name = "Italian", native = "Italiano", rtl = false)
        ),
        emoji = "🇨🇭",
        emojiU = "U+1F1E8 U+1F1ED"
    ),
    CountriesQuery.Country(
        code = "LA",
        name = "Laos",
        native = "ສປປລາວ",
        phone = "856",
        continent = CountriesQuery.Continent(code = "AS", name = "Asia"),
        currency = "LAK",
        languages = listOf(CountriesQuery.Language(code = "lo", name = "Laotian", native = "ລາວ / Pha xa lao", rtl = false)),
        emoji = "🇱🇦",
        emojiU = "U+1F1F1 U+1F1E6"
    ),
)
