package com.example.appexamen.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.appexamen.R
import com.example.appexamen.model.ColorStart
import com.example.appexamen.model.ColoresNumeros
import com.example.appexamen.viewmodel.ViewModel
import kotlinx.coroutines.delay

@Composable
fun Game(viewModel: ViewModel, text: String) {
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondojuegoexamen)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            NombreJUgador(text)
            ShowAciertos(aciertos)
        }
        Column(modifier = Modifier
            .padding(top = 130.dp)) {
            Row {
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[0], Color.Red, "1", Color.White)
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[1], Color.Yellow, "2", Color.White)

            }
            Row {
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[2], Color.Cyan, "3", Color.White)
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[3], Color.DarkGray, "4", Color.White)
            }
            Row {
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[4], Color.Green, "5", Color.White)
                CreateButton(viewModel, viewModel.getListaNumerosBotones()[5], Color.Blue, "6", Color.White)
            }
            Row {
                StartButton(viewModel, ColorStart.START_COLOR)
            }


        }

    }
}

@Composable
fun NombreJUgador(text: String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 100.dp)
    ) {

        Text(
            text = "Jugador: $text",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun CreateButton(viewModel: ViewModel, numeroColor:Int, color: Color, textButton:String, colorLetra:Color) {

    var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.buttonColorActivo) }

    viewModel.estadoJuegoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoJuegoLiveData.value!!.buttonColorActivo
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.addNumber(numeroColor, viewModel.getRandom())
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
            .size(width = 150.dp, height = 60.dp)
    ) {
       Text(
           text = textButton,
           fontSize = 20.sp,
           fontWeight = FontWeight.ExtraBold,
           color = colorLetra
       )
    }
}

@Composable
fun StartButton(viewModel: ViewModel, color: ColorStart) {
    var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.startActivo) }
    var _color by remember { mutableStateOf(color.colorInicio) }
    viewModel.estadoJuegoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoJuegoLiveData.value!!.startActivo
    }

    LaunchedEffect(_color){
        while(_activo){
            delay(1000)
            _color = color.colorParpadeo
            delay(1000)
            _color = color.colorInicio
            delay(1000)
        }
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.setRandom()
        },
        colors = ButtonDefaults.buttonColors(_color),
        modifier = Modifier
            .padding(top = 60.dp, start = 90.dp)
            .size(width = 200.dp, height = 100.dp)
            .clip(CircleShape)
    ) {
        Text(
            text = "Start",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowAciertos(aciertos:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 115.dp)
        ) {

        Text(text = "Aciertos: $aciertos" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)

    }

}




