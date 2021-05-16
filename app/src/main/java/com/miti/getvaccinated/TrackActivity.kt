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
import androidx.compose.ui.Alignment
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
    var name: String = ""
    var city: String = ""
    var email: String = ""
    var phone: String = ""
    var cnic: String = ""
    var status: String = ""

    dbRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val model: ApplicationModel? = snapshot.getValue(ApplicationModel::class.java)
            name = model!!.displayName.toString()
            city = model!!.city.toString()
            email = model!!.email.toString()
            phone = model!!.phoneno.toString()
            cnic = model!!.cnic.toString()
            status = model!!.status.toString()
            Toast.makeText(context, "Status: " + model!!.status, Toast.LENGTH_SHORT)
                .show()
        }

        override fun onCancelled(error: DatabaseError) {}
    })

    Track(name,city,email,phone,cnic,status)

}

@Composable
fun Track(name: String, city: String, email: String, phone: String, cnic: String, status: String) {


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.dp, color = Color.White),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Name: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "City: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = city,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Email: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = email,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Phone: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = phone,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "CNIC: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = cnic,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Status: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = status,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}