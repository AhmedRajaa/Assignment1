package com.example.assignment1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var contaxt:Context, var datailsList:ArrayList<DataClassContactdDetails>) :RecyclerView.Adapter<MyAdapter.MyViewwHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewwHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.contactdetails_item
       ,parent,false)
        return MyViewwHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewwHolder, position: Int) {
    val currentitem=datailsList[position]
        holder.name.text=currentitem.name
        holder.number.text=currentitem.number
        holder.address.text=currentitem.address
    }

    override fun getItemCount(): Int {
        return datailsList.size
    }
    class  MyViewwHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name_item)
        val number:TextView=itemView.findViewById(R.id.number_item)
        val address:TextView=itemView.findViewById(R.id.address_item)
    }
}