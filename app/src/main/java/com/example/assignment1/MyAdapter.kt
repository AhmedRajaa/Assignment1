package com.example.assignment1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyAdapter(var context:Context, var datailsList:ArrayList<DataClassContactdDetails>) :RecyclerView.Adapter<MyAdapter.MyViewwHolder>(){
        val db = Firebase.firestore

    class  MyViewwHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name_item)
        val number:TextView=itemView.findViewById(R.id.number_item)
        val address:TextView=itemView.findViewById(R.id.address_item)
        val btnDelete:Button=itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewwHolder {
           val itemView=LayoutInflater.from(parent.context).inflate(R.layout.contactdetails_item
           ,parent,false)
            return MyViewwHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewwHolder, position: Int) {
        val currentItem=datailsList[position]
            holder.name.text=currentItem.name
            holder.number.text=currentItem.number
            holder.address.text=currentItem.address

            holder.btnDelete.setOnClickListener {
                deleteContact(currentItem)
            }
        }

        override fun getItemCount(): Int {
            return datailsList.size
        }

        private fun deleteContact(contact: DataClassContactdDetails) {
            db.collection("Contacts").document(contact.id!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Contact deleted", Toast.LENGTH_SHORT).show()
                    datailsList.remove(contact)
                    notifyDataSetChanged()
                }

                .addOnFailureListener { error ->
                    Toast.makeText(context, "Error.${error.message}", Toast.LENGTH_SHORT)
                        .show()
                }
        }

    }