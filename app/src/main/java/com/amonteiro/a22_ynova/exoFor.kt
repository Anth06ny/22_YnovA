package com.amonteiro.a22_ynova


fun main() {
    val texte = "Une phrase avec des e"
    println("nbA : ##" + nbA(texte) + "##")
}

fun reverseV2(s: String): String {
    var result = ""
    for (i in s.length-1 downTo 0) {
        result += s[i]
    }
    return result
}

fun reverse(s: String): String {
    var result = ""
    for (c in s) {
        result = c + result
    }
    return result
}

fun nbALambda(s: String) = s.count { it == 'a'  }

fun nbA(s: String): Int {
    var result = 0;
    for (c in s) {
        if (c == 'a') {
            result++
        }
    }

    for (i in s.indices) {
        if (s[i] == 'a') {
            result++
        }
    }
    return result
}