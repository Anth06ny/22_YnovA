package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a22_ynova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

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



}