package com.amonteiro.a22_ynova

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a22_ynova.databinding.ActivityMainBinding
import java.util.*

const val MENU_TIME_PICKER = 15
const val MENU_WEATHER = 16

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //Donnée
    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Afficher mon IHM
        setContentView(binding.root)

        binding.btValidate.setOnClickListener {
            if (binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            } else if (binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            binding.imageView.setImageResource(R.drawable.ic_baseline_flag_24)
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("")
            binding.rg.clearCheck()
            binding.imageView.setImageResource(R.drawable.ic_baseline_delete_forever_24)
        }
    }


    //Callback de la création du menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menu.add(0, MENU_TIME_PICKER, 0, "TimePicker")
        menu.add(0, MENU_WEATHER, 0, "Météo")

        return super.onCreateOptionsMenu(menu)
    }

    //Callback d'un click sur un menuitem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_TIME_PICKER) {
            TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        } else if (MENU_WEATHER == item.itemId) {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    //Callback du TimePicker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this, "$hourOfDay:$minute", Toast.LENGTH_LONG).show()
        //Modification du temps stocké
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
    }

}