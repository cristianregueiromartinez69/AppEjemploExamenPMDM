package com.example.appexamen.model


enum class Estados(val enterActivo:Boolean) {
    VACIO(enterActivo = false),
    COMPLETO(enterActivo = true)
}

enum class EstadosJugando(val startActivo:Boolean, val buttonColorActivo:Boolean){
    INICIO(startActivo = true, buttonColorActivo = false),
    GENERANDO(startActivo = false, buttonColorActivo = false),
    ADIVINANDO(startActivo = false, buttonColorActivo = true)
}

