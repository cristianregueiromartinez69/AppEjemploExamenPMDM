package com.example.appexamen.model

import androidx.lifecycle.MutableLiveData

object Datos {

    var nombre = ""
    var random = 0
    var numeroJugador = 0
    var listaNumerosBotones : MutableList<Int> = mutableListOf(1,2,3,4,5,6)
    var aciertos = 0
}