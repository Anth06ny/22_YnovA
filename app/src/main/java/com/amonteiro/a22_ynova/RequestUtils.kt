package com.amonteiro.a22_ynova

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


const val URL_API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

const val URL_API_POKEMON_1 = "https://www.amonteiro.fr/api/pokemonN1"

object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadPokemon(): PokemonBean {
        var json = sendGet(URL_API_POKEMON_1)
        return gson.fromJson(json, PokemonBean::class.java )
    }

    fun loadWeather(cityName:String): WeatherBean {
        var json = sendGet(URL_API_WEATHER.format(cityName))
        return gson.fromJson(json, WeatherBean::class.java )
    }

    fun loadWeather2(cityName:String)= gson.fromJson(sendGet(URL_API_WEATHER.format(cityName)), WeatherBean::class.java )


    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}