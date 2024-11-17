package com.example.appexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appexamen.ui.theme.AppExamenTheme
import com.example.appexamen.view.MyApp
import com.example.appexamen.viewmodel.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModel()
        enableEdgeToEdge()
        setContent {

            AppExamenTheme {
                MyApp(viewModel)
            }

        }
    }
}


//TODO: hacer que salga una u otra ventana
