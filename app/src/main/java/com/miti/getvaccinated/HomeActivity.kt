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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    MainContent(this)
                }
            }
        }
    }
}

@Composable
fun MainContent(context: Context) {
    Column {
        Row {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(0.50f)
                    .padding(start = 10.dp, end = 5.dp, top = 10.dp, bottom = 10.dp)
                    .clickable {
                        val intent = Intent(context, SendRequestActivity::class.java)
                        context.startActivity(intent)
                    },
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
                    .padding(start = 5.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clickable {

                    },
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .width(50.dp)
                            .height(50.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_data_saver_off_24),
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
        Row {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(0.50f)
                    .padding(start = 10.dp, end = 5.dp, top = 10.dp, bottom = 10.dp)
                    .clickable {
                        FirebaseAuth
                            .getInstance()
                            .signOut()
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra("EXIT", true)
                        context.startActivity(intent)
                    },
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
                        text = "Sign Out",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
