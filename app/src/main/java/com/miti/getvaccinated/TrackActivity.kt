package com.miti.getvaccinated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
fun Track(dbRef: DatabaseReference, trackActivity: TrackActivity) {

}