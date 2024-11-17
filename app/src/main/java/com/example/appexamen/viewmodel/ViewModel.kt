package com.example.appexamen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appexamen.model.Datos
import com.example.appexamen.model.Estados

class ViewModel:ViewModel(){

    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.VACIO)
    private val _nombreLiveData = MutableLiveData<String>()
    val nombreLiveData : LiveData<String> get() = _nombreLiveData

    init {
        _nombreLiveData.value = Datos.nombre
    }


    fun checkText(texto:String){
        if (texto.isEmpty()){
            getEstadoVacio()
        }
        else{
            getEstadoCompleto()
        }
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
}