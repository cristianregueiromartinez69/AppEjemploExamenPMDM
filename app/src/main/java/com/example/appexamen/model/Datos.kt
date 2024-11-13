package com.example.appexamen.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Clase datos con los datos de la aplicacion
 */
object Datos {

    /**
     * Aqui tenemos los singletons de:
     * 1. las rondas del juego
     * 2. el record el jugador
     * 3. los aciertos del jugador
     * 4. la lista de la maquina que se va generando en cada ronda
     * 5. la lista del jugador al pulsar botones
     */
    var rondas = mutableStateOf(0);
    var aciertos = mutableStateOf(0);
    var record = mutableStateOf(0);
    var secuenciaJuego = mutableListOf<Int>()
    var secuenciaJugador = mutableListOf<Int>()

}

/**
 * Clase enum con los numeros, colores y colores iluminados
 */
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
