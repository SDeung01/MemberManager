package com.example.membermanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membermanager.databinding.ActivityMemberListBinding

class MemberListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemberListBinding
    var memberList: MutableList<Member>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memberDAO = MemberDAO(this)
        memberList = memberDAO.showMemberList()
        memberDAO.close()

        val layoutManager = LinearLayoutManager(this)
        binding.memberRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(memberList)
        binding.memberRecyclerView.adapter=adapter
        binding.memberRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )


    }
}