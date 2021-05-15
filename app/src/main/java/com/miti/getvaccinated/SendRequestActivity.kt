package com.miti.getvaccinated

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miti.getvaccinated.Model.ApplicationModel
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth


class SendRequestActivity : AppCompatActivity() {

    var db: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseDatabase.getInstance()
        dbRef = db!!.getReference("Applications")


        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    Send(dbRef!!, this)
                }
            }
        }
    }
}

@Composable
fun Send(dbRef: DatabaseReference, context: Context) {
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    val cnicValue = remember { mutableStateOf("") }
    val cityValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val signInAccount = GoogleSignIn.getLastSignedInAccount(context)
    var displayName = ""
    var email = ""

    if (signInAccount != null) {
        displayName = signInAccount.displayName.toString()
        email = signInAccount.email.toString()
    }

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
                    value = cnicValue.value,
                    onValueChange = { cnicValue.value = it },
                    label = { Text(text = "CNIC NO") },
                    placeholder = { Text(text = "CNIC") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = cityValue.value,
                    onValueChange = { cityValue.value = it },
                    label = { Text(text = "City") },
                    placeholder = { Text(text = "City") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = phoneValue.value,
                    onValueChange = { phoneValue.value = it },
                    label = { Text(text = "Phone") },
                    placeholder = { Text(text = "Phone") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        val model = ApplicationModel(
                            cnicValue.value,
                            cityValue.value,
                            phoneValue.value,
                            displayName,
                            email
                        )
                        dbRef.child(uid).setValue(model)
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
