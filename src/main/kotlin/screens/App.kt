package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import services.core.VlcService
import java.awt.GraphicsEnvironment




@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var isDesktop by remember { mutableStateOf(false) }
    val vlc = VlcService()
    detectarPantallas()



    MaterialTheme {
        Button(onClick = {
            text = if (isDesktop) "Hello, World!" else "Hello, Desktop!"
            isDesktop = !isDesktop

            if (!isDesktop){
                vlc.open()
            }else {
                vlc.close()
            }

        }) {
            Text(text)
        }
    }
}



fun detectarPantallas() {
    val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
    val pantallas = ge.screenDevices

    println("Número de pantallas detectadas: ${pantallas.size}")
    pantallas.forEachIndexed { index, pantalla ->
        val bounds = pantalla.defaultConfiguration.bounds
        println("Pantalla $index: ${bounds.width}x${bounds.height} en (${bounds.x}, ${bounds.y})")
    }
}