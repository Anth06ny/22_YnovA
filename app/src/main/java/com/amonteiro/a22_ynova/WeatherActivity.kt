package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynova.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            thread {

                model.loadData("Toulouse")
                //Execution garantie sur l'UIThread
                runOnUiThread {
                    resreshScreen()
                }
            }
        }

        resreshScreen()
    }

    fun resreshScreen(){


        /* -------------------------------- */
        // Gestion de la donnée cas qui marche
        /* -------------------------------- */
        //Affichage des données version kotlin
        binding.tv.text = model.weather?.name ?: "-"
        binding.tvWind.text = "${model.weather?.wind?.speed ?: "-"}"
        binding.tvMinMax.text = "(${model.weather?.temperature?.temp_min ?: "-"}°/${model.weather?.temperature?.temp_max ?: "-"}°)"

        //Version classique
        if(model.weather !=null) {
            binding.tvTemp.text = "${model.weather?.temperature?.temp}°"
        }
        else {
            binding.tvTemp.text = "-°"
        }

        //Version avec getOrNull pour eviter l'indexOutOfBoundException (hors de la liste)
        binding.tvDesc.text = model.weather?.weather?.getOrNull(0)?.description ?: "-"

        //Version Kotlin
//                    model.weather?.weather?.get(0)?.icon?.let {
//                        Picasso.get().load("https://openweathermap.org/img/wn/${model.weather?.weather?.get(0)?.icon}@4x.png")
//                            .placeholder(R.mipmap.ic_launcher)
//                            .error(R.drawable.ic_baseline_delete_forever_24)
//                            .into(binding.ivTemp)
//                    } ?: binding.ivTemp.setImageDrawable(null)

        if (!model.weather?.weather.isNullOrEmpty()) {
            Picasso.get().load("https://openweathermap.org/img/wn/${model.weather?.weather?.get(0)?.icon}@4x.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_baseline_delete_forever_24)
                .into(binding.ivTemp)
        }
        else {
            binding.ivTemp.setImageDrawable(null)
        }

        /* -------------------------------- */
        // Cas qui marche pas
        /* -------------------------------- */
        binding.tvError.text = model.errorMessage
        binding.tvError.isVisible = model.errorMessage.isNotBlank()

        binding.progressBar.isVisible = false
    }
}

