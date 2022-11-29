package com.amonteiro.a22_ynova

import com.google.gson.annotations.SerializedName
import java.util.*

fun main() {
    val randomName = RandomName()
    randomName.add("bobby")
    repeat(10) {
        println(randomName.next() + " ")
    }
}

class RandomName(){
    private val list = arrayListOf("Toto", "Tata", "Tobby")
    private val random = Random()
    private var lastValue = ""


    fun add(name: String?) = if(!name.isNullOrBlank() && name !in list) list.add(name)  else false

    fun next(): String {
        return  list[random.nextInt(list.size)]
    }

    fun nextV2()= list.random()


    fun nextDiff(): String {
        var value = next()
        while(value == lastValue) {
            value = next()
        }

        lastValue = value
        return value
    }

    fun nextDiffV2()= list.filter { it != lastValue }.random().also { lastValue = it}

    fun next2() = Pair(nextDiff(), nextDiff())

}

class PlaneBean(name: String) {
    var id = name.hashCode()
        private set

    var name = name
        set(value) {
            field = value
            id = field.hashCode()
        }
}

class UserBean(val name: String, var note: Int = 0) {
    val id = name.hashCode()
}

class PrintRandomIntBean(val max: Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println(random.nextInt(max))
    }
}

data class StudentBean(val name: String) {
    var note = 0
}

/* -------------------------------- */
// API Weather
/* -------------------------------- */

data class WeatherBean(@SerializedName("main") var temperature : TempBean, var wind:WindBean,var name:String)

data class WindBean(var speed : Double)

data class TempBean(var temp : Double)

/* -------------------------------- */
// API Pokemon
/* -------------------------------- */
data class PokemonBean(var name:String, var type : String)

