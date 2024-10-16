package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import moe.tlaster.precompose.PreComposeApp

@Composable
@Preview
fun App() {

    PreComposeApp {

        val colors = getColorsTheme()

        AppTheme {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Bienvenidos", color = colors.TextColor)
                Text("Curso KMP : PreComposeApp")
                Text("Curso KMP : PreComposeApp_2")
                Text("Curso KMP : PreComposeApp_3")
            }
        }
    }

    /*
    Ejemplo {  }
    Ejemplo (click = {

    })
    */
}

fun Ejemplo(click: () -> Unit) {

}

// reconoce clases de la libreria de Navegación
class CustomVM : ViewModel() {

}