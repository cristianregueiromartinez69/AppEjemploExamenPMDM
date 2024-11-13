package com.example.appexamen.model

import androidx.compose.ui.graphics.Color

object Datos {

    var rondas = 0;
    var aciertos = 0;
    var record = 0;
    var secuenciaJuego = mutableListOf<Int>()
    var sencuenciaJugador = mutableListOf<Int>()

}

enum class Colores(val numColor:Int, val colorInicial: Color, val colorIluminado:Color){
    ROJO(1, Color(0xFF9A0000), Color(0xFFFF0000)),
    VERDE(2, Color(0xFF00FF2A), Color(0xff006A11)),
    AZUL(3, Color(0xFF0059ff), Color(0xFF002364)),
    AMARILLO(4, Color(0xFFF0FF00), Color(0xFF7f8500))
}

enum class Estados(val startActivo:Boolean, val botonesColoresActivos:Boolean){
    INICIO(startActivo = true, botonesColoresActivos = false),
    GENERANDO(startActivo = false, botonesColoresActivos = true),
    ADIVINANDO(startActivo = false, botonesColoresActivos = true)
}
