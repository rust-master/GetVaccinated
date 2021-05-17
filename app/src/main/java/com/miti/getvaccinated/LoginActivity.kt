package com.miti.getvaccinated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    LoginContent()
                }
            }
        }
    }
}

@Composable
fun LoginContent() {

}