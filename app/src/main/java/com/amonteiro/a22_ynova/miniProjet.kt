package com.amonteiro.a22_ynova

fun main() {

    var weather = RequestUtils.loadWeather("Toulouse")
    println("Il fait ${weather.temperature.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} km/h")

    var pokemon = RequestUtils.loadPokemon()
    println("${pokemon.name} est de type ${pokemon.type}")
}