package com.miti.getvaccinated

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miti.getvaccinated.ui.theme.GetVaccinatedTheme
import com.google.firebase.database.DatabaseError

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import com.miti.getvaccinated.Model.ApplicationModel


class TrackActivity : AppCompatActivity() {

    private var db: FirebaseDatabase? = null
    private var dbRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance()
        dbRef = db!!.getReference("Applications").child(uid)

        setContent {
            GetVaccinatedTheme {
                Surface(color = Color.White) {
                    ApplicationData(dbRef!!, this)
                }
            }
        }
    }
}

@Composable
fun ApplicationData(dbRef: DatabaseReference, context: Context) {
    val name = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val cnicValue = remember { mutableStateOf("") }
    val status = remember { mutableStateOf("") }

    dbRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val model: ApplicationModel? = snapshot.getValue(ApplicationModel::class.java)
            name.value = model!!.displayName.toString()
            city.value = model.city.toString()
            email.value = model.email.toString()
            phone.value = model.phoneno.toString()
            cnicValue.value = model.cnic.toString()
            status.value = model.status.toString()
            Toast.makeText(context, "Status: " + model.status, Toast.LENGTH_SHORT)
                .show()
        }

        override fun onCancelled(error: DatabaseError) {}
    })

    Track(name, city, email, phone, cnicValue, status)

}

@Composable
fun Track(
    name: MutableState<String>,
    city: MutableState<String>,
    email: MutableState<String>,
    phone: MutableState<String>,
    cnic: MutableState<String>,
    status: MutableState<String>
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.dp, color = Color.White),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "Name: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = name.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "City: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = city.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "Email: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = email.value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "Phone: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = phone.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "CNIC: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = cnic.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                    text = "Status: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = status.value,
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}