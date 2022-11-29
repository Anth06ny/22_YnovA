package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a22_ynova.databinding.ActivityWeatherBinding
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
                    binding.tvData.setText("Il fait ${weather.temperature.temp} °  à ${weather.name}")
                    binding.progressBar.isVisible = false
                }
            }
        }
    }
}