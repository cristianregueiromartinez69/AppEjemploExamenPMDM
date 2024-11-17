package com.example.appexamen.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appexamen.R
import com.example.appexamen.viewmodel.ViewModel

@Composable
fun Game(viewModel: ViewModel, text: String) {

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
            NombreJUgador(text)
        }
        Column(modifier = Modifier
            .padding(top = 130.dp)) {
            Row {
                CreateButton(Color.Red)
                CreateButton(Color.Yellow)

            }
            Row {
                CreateButton(Color.Cyan)
                CreateButton(Color.DarkGray)
            }
            Row {
                CreateButton(Color.Green)
                CreateButton(Color.Blue)
            }
            Row {
                StartButton(Color.Magenta)
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
            .padding(top = 30.dp, start = 90.dp)
    ) {

        Text(
            text = "Jugador: $text",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CreateButton(color: Color) {
    Button(
        onClick = {
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
            .size(width = 150.dp, height = 60.dp)
    ) {

    }
}

@Composable
fun StartButton(color: Color) {
    Button(
        onClick = {
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 90.dp)
            .size(width = 200.dp, height = 100.dp)
            .clip(CircleShape)
    ) {

    }
}




