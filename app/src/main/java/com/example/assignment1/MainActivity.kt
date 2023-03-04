package com.example.assignment1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val name = findViewById<EditText>(R.id.Name)
        val number = findViewById<EditText>(R.id.Number)
        val address = findViewById<EditText>(R.id.Address)
        val buttonSave = findViewById<Button>(R.id.btn_save)
        val buttonShowContact=findViewById<Button>(R.id.button2)


        val db = Firebase.firestore

        buttonSave.setOnClickListener {
            var namdText = name.text.toString()
            var numberText = number.text.toString()
            var addressText = address.text.toString()

            val contactdetails = hashMapOf(
                "name" to namdText,
                "number" to numberText,
                "address" to addressText
            )
            db.collection("Contacts")
                .add(contactdetails)
                .addOnSuccessListener { documentReference ->
                    name.text.clear()
                    number.text.clear()
                    address.text.clear()
                    Toast.makeText(applicationContext, "Add ${documentReference.id}", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext, "${e.message}", Toast.LENGTH_LONG).show()
                }
        }
        buttonShowContact.setOnClickListener {
            val intent=Intent(applicationContext,AllContactsActivity::class.java)
            startActivity(intent)
        }
    }


}