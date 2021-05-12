package com.miti.getvaccinated


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

}
