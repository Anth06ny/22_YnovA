package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynova.databinding.ActivityWeatherAroundBinding

class WeatherAroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    var count = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btAdd.setOnClickListener {
            model.list.add(CoordBean(count++, count++))
            refreshScreen()
        }

        binding.btDelete.setOnClickListener {
            //model.list.removeAt(0) provoque indexOutOfBound exception si pas d'element
            model.list.removeFirstOrNull()
            refreshScreen()
        }

        refreshScreen()
    }

    fun refreshScreen(){
        //v1
        var texte = ""
        for (coordBean in model.list) {
            texte += "${coordBean.lat}, ${coordBean.long}\n"
        }
        binding.tv.text = texte

        //V2
        binding.tv.text = model.list.joinToString("\n") { "${it.lat}, ${it.long}" }
    }

}