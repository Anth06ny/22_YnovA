package com.amonteiro.a22_ynova

import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    var weather : WeatherBean? = null
    var errorMessage = ""

    fun loadData(cityName : String){

        //Reset donnée
        errorMessage = ""
        weather = null

        try {
            //Appel on va chercher les donner
            weather = RequestUtils.loadWeather(cityName)
        }
        catch (e: Exception) {
            e.printStackTrace()
            errorMessage = "UNe erreur est survenue : ${e.message}"
        }
    }


}