package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a22_ynova.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            binding.progressBar.isVisible = true

            thread {
                //Appel on va chercher les donner
                val weather = RequestUtils.loadWeather("Toulouse")

                //Execution garantie sur l'UIThread
                runOnUiThread {
                    //Affichage des données
                    binding.tv.text = weather.name
                    binding.tvTemp.text = "${weather.temperature.temp}°"
                    binding.tvWind.text = "${weather.wind.speed}"
                    binding.tvMinMax.text = "(${weather.temperature.temp_min}°/${weather.temperature.temp_max}°)"

                    if (weather.weather.isNotEmpty()) {
                        binding.tvDesc.text = weather.weather[0].description

                        Picasso.get().load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png")
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.drawable.ic_baseline_delete_forever_24)
                            .into(binding.ivTemp)
                    }



                    binding.progressBar.isVisible = false
                }
            }
        }
    }
}