package com.example.membermanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.membermanager.databinding.ActivityAddMemberBinding
import com.example.membermanager.databinding.ActivityMainBinding

class AddMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addBtn = binding.addBtn

        addBtn.setOnClickListener {
            val name: String = binding.inputName.text.toString()
            val age: String = binding.inputAge.text.toString()
            val mobile: String = binding.inputMobile.text.toString()
            if(name.length == 0){
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else if(age.length == 0){
                Toast.makeText(this, "나이를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else if(mobile.length == 0){
                Toast.makeText(this, "연락처를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "등록되었습니다.", Toast.LENGTH_SHORT).show()
                val addMember = Member(name, age, mobile)
                val memberDAO = MemberDAO(this)
                memberDAO.addMember(addMember)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}