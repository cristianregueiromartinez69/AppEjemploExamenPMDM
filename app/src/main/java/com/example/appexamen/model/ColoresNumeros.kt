package com.example.appexamen.model

import androidx.compose.ui.graphics.Color

enum class ColoresNumeros(var colorValor:Int) {

    ROJO(colorValor = 1),
    AMARILLO(colorValor = 2),
    CYAN(colorValor = 3),
    GRIS(colorValor = 4),
    VERDE(colorValor = 5),
    AZUL(colorValor = 6)

}

enum class ColorStart(val colorInicio: Color, val colorParpadeo:Color = Color(0xFFFFB9FF)){
    START_COLOR(colorInicio = Color.Magenta)
}