package com.example.appexamen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appexamen.R
import com.example.appexamen.viewmodel.ViewModel

@Composable
fun MyApp(viewModel: ViewModel) {
    var pulsado by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondoinicio)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            if (!pulsado){
                Login({ pulsado = true }, viewModel, remember { mutableStateOf(text) })
            }
            else{
                Game(viewModel)
            }
        }
    }
}

@Composable
fun Login(funcion:() -> Unit, viewModel: ViewModel, texto: MutableState<String>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 30.dp)
    ) {

        Row {
            TextoInicial(texto = "¡Adivina el numero!")
        }
        Row {
            NombreInicio()
        }
        Row {
            TextNombreEscribir(texto)
        }
        Row {
            Buttonenter(funcion, viewModel, texto)
        }

    }
}

@Composable
fun TextoInicial(texto: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 35.dp)
    ) {
        Text(
            text = texto,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NombreInicio(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 100.dp, start = 35.dp)
    ) {
        Text(
            text = "Nombre",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun TextNombreEscribir(text: MutableState<String>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp, start = 16.dp)
    ) {
    }
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        },
        placeholder = { Text("Nombre aquí...") }
    )
}

@Composable
fun Buttonenter(funcion: () -> Unit, viewModel: ViewModel, texto: MutableState<String>){

    viewModel.checkText(texto.value)

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.enterActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.enterActivo
    }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp, start = 30.dp)) {
        Button(
            enabled = _activo,
            onClick = {
                funcion()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            ),
            modifier = Modifier
                .padding(top = 80.dp)
                .size(width = 150.dp, height = 60.dp)
        ){
            Text(
                text = "Enter",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}



