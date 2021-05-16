package com.miti.getvaccinated

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme

class TrackActivity : AppCompatActivity() {

    private var db: FirebaseDatabase? = null
    private var dbRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    Track(dbRef!!, this)
                }
            }
        }
    }
}

@Composable
fun Track(dbRef: DatabaseReference, context: Context) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .shadow(10.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp)

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row() {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Name: ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Zaryab",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}