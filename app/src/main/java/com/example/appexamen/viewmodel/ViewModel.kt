package com.example.appexamen.viewmodel
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appexamen.model.Datos
import com.example.appexamen.model.Datos.record
import com.example.appexamen.model.Datos.rondas
import com.example.appexamen.model.Datos.aciertos
import com.example.appexamen.model.Datos.secuenciaJuego
import com.example.appexamen.model.Datos.secuenciaJugador
import com.example.appexamen.model.Estados.GENERANDO
import com.example.appexamen.model.Estados.INICIO
import com.example.appexamen.model.Estados

/**
 * Clase viewmodel con la logica del somin dice
 * @author cristian
 * @version 1.0
 */
class ViewModel : ViewModel(){

    //variable estado para activar o desactivar botones
    var estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    //metodo para aumentar rondas
    fun aumentarRonda() {
        rondas.value += 1
    }

    fun aumentarAciertos(){
        aciertos.value += 1
    }

    fun aumentarRecord(){
        if(record.value < aciertos.value){
            record.value = aciertos.value
        }
    }
    //metodo para generar una secuencia
    fun generarSecuencia() {
        aumentarRonda()
        secuenciaJuego.add((0..3).random())
        estadoLiveData.value = GENERANDO
    }

    //metodo onclick para los colores
    fun click(buttonId: Int, context:Context){
        secuenciaJuego.add(buttonId)
        comprobarSecuencia(context)
    }

    //metodo para comprobar si la secuencia es o no es correcta
    fun comprobarSecuencia(context: Context):Boolean{
        if (secuenciaJuego == secuenciaJugador){
            secuenciaJugador.clear()
            Log.d("TAG", "CORRECTO")
            aumentarAciertos()
            aumentarRonda()
            aumentarRecord()
            estadoLiveData.value = Estados.INICIO
            return true
        }
        else if (secuenciaJuego.subList(0, secuenciaJugador.size) == secuenciaJugador){
            Log.d("TAG", "CORRECTO")
            return true
        }
        else{
            secuenciaJugador.clear()
            secuenciaJuego.clear()
            rondas.value = 0
            aciertos.value = 0
            Log.d("TAG", "INCORRECTO")
            estadoLiveData.value = INICIO
            return false
        }
    }

    fun getAciertos():MutableState<Int>{
        return aciertos
    }

    fun getRondas():MutableState<Int>{
        return rondas
    }

    fun getRecord():MutableState<Int>{
        return record
    }











}