package com.example.assignment1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllContactsActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var datailsRecyclerview: RecyclerView
    val contactsArray = ArrayList<DataClassContactdDetails>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_contacts)

        datailsRecyclerview = findViewById(R.id.recyclerview)
        getData()

    }

    private fun getData() {
        db.collection("Contacts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val contact = DataClassContactdDetails(
                        document.id,
                        document.getString("name"),
                        document.getString("number"),
                        document.getString("address")
                    )
                    contactsArray.add(contact)
                }
                val adapter = MyAdapter(this, contactsArray)
                datailsRecyclerview.adapter=adapter
                datailsRecyclerview.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error, ${exception.message}", Toast.LENGTH_SHORT).show()
                //  Log.w(TAG, "Error getting documents.", exception)
            }
    }

}