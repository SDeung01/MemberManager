package com.example.membermanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.membermanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addMemberBtn = binding.addMemberBtn
        val showMemberListBtn = binding.showMemberListBtn

        addMemberBtn.setOnClickListener {
            val intent = Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }

        showMemberListBtn.setOnClickListener {
            val intent = Intent(this, MemberListActivity::class.java)
            startActivity(intent)
        }
    }

}