package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.a22_ynova.databinding.ActivityWeatherAroundBinding

class WeatherAroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    val adapter = WeatherListAdapter()

    var count = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //reglage RecyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.btAdd.setOnClickListener {
            model.list.add(0, CoordBean(count++, count++))
            //Version TextView
            refreshScreen()

            //Version RecyclerView
            //Creation d'une nouvelle liste car submit List ne s'actualise pas si c'est la même
            adapter.submitList(model.list.toList())
        }

        binding.btDelete.setOnClickListener {
            //model.list.removeAt(0) provoque indexOutOfBound exception si pas d'element
            model.list.removeFirstOrNull()
            refreshScreen()

            adapter.submitList(model.list.toList())
        }

        refreshScreen()
        adapter.submitList(model.list.toList())
    }

    fun refreshScreen() {
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