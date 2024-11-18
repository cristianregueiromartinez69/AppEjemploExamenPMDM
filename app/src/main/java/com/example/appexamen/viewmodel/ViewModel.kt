package com.example.appexamen.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appexamen.model.ColorStart
import com.example.appexamen.model.Datos
import com.example.appexamen.model.Estados
import com.example.appexamen.model.EstadosJugando
import kotlin.random.Random

class ViewModel:ViewModel(){

    val random = Random

    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.VACIO)
    val estadoJuegoLiveData : MutableLiveData<EstadosJugando> = MutableLiveData(EstadosJugando.INICIO)

    private val _nombreLiveData = MutableLiveData<String>()
    val nombreLiveData : LiveData<String> get() = _nombreLiveData

    private var _aciertosLiveData = MutableLiveData<Int>()
    val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData

    init {
        _nombreLiveData.value = Datos.nombre
        _aciertosLiveData.value = Datos.aciertos
    }


    fun checkText(texto:String){
        if (texto.isEmpty()){
            getEstadoVacio()
        }
        else{
            getEstadoCompleto()
        }
    }

    fun setRandom(){
        estadoJuegoLiveData.value = EstadosJugando.GENERANDO
        var numeroRandom = random.nextInt(5) + 1
        Log.d("random", numeroRandom.toString())
        Datos.random = numeroRandom
        estadoJuegoLiveData.value = EstadosJugando.ADIVINANDO
    }

    fun addNumber(numeroColor:Int, numeroRandom:Int){
        Datos.numeroJugador = numeroColor
        winOrLose(numeroColor, numeroRandom)
    }

    fun winOrLose(numeroJugador:Int, numeroMaquina:Int):Boolean{
        if(numeroJugador == numeroMaquina){
            estadoJuegoLiveData.value = EstadosJugando.INICIO
            setAciertos()
            Log.d("random", "He ganado")
            return true
        }
        estadoJuegoLiveData.value = EstadosJugando.INICIO
        Log.d("random", "He perdido")
        return false
    }

    fun setNombre(nombre:String){
        Datos.nombre = nombre
        _nombreLiveData.value = Datos.nombre
    }

    fun getNombre():String{
        return Datos.nombre
    }

    fun getEstadoVacio(){
        estadoLiveData.value = Estados.VACIO
    }

    fun getEstadoCompleto(){
        estadoLiveData.value = Estados.COMPLETO
    }

    fun getListaNumerosBotones():MutableList<Int>{
        return Datos.listaNumerosBotones
    }

    fun getRandom():Int{
        return Datos.random
    }

    fun setAciertos(){
        Datos.aciertos += 1
        _aciertosLiveData.value = Datos.aciertos
    }

    fun getAciertos():Int{
        return Datos.aciertos
    }




}