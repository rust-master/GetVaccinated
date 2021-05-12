package com.miti.getvaccinated

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miti.getvaccinated.Model.ApplicationModel
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    var db: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseDatabase.getInstance();
        dbRef = db!!.getReference("Applications");

        setContent {
            GetVaccinatedTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    // App(dbRef!!)
                    MainContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainContent() {

    Column() {
        Row() {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(0.50f)
                    .padding(start = 10.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp)

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .width(50.dp)
                            .height(50.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_article_24),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Application",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(1.50f)
                    .padding(start = 5.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .width(50.dp)
                            .height(50.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_article_24),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Track",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun App(dbRef: DatabaseReference) {

    val CNICValue = remember { mutableStateOf("") }
    val CityValue = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(10.dp)

        ) {
            Text(
                text = "Application",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 20.sp,
                ),
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = CNICValue.value,
                    onValueChange = { CNICValue.value = it },
                    label = { Text(text = "CNIC NO") },
                    placeholder = { Text(text = "CNIC") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = CityValue.value,
                    onValueChange = { CityValue.value = it },
                    label = { Text(text = "City") },
                    placeholder = { Text(text = "City") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        val model = ApplicationModel(CNICValue.value, CityValue.value)
                        dbRef.child(CNICValue.value).setValue(model)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Send", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(20.dp))

            }
        }
    }
}
