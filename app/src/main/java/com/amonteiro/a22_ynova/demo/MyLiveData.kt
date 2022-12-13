package com.amonteiro.a22_ynova.demo


var toto = MyLiveData("")

fun main() {

    toto.postValue("0")
    toto.postValue("a")

    observeur()
    toto.postValue("b")
    toto.postValue("c")
}

fun observeur() {

    toto.action = {
            println("Nouvelle valeur : $it")
    }

}

fun modificateur() {

}


class MyLiveData(var value: String? = null) {

    var action: ((String?) -> Unit)? = null
        set(value) {
            field = value
            action?.invoke(this.value)
        }

    fun postValue(value: String?) {
        this.value = value
            action?.invoke(value)
    }

}