package com.example.membermanager

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.membermanager.databinding.ItemRecyclerviewBinding


class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<Member>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        binding.memberId.text= datas!![position].memberId
        binding.memberName.text= datas!![position].name
        binding.memberAge.text= datas!![position].age
        binding.memberMobile.text= datas!![position].mobile

        val context = holder.itemView.context

        holder.itemView.setOnClickListener{
            Intent(context, MemberDetailActivity::class.java).apply {
                putExtra("memberId", binding.memberId.text)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivities(arrayOf(this)) }
        }
    }
}
