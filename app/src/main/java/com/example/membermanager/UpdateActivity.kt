package com.example.membermanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.membermanager.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var memberId :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        memberId = intent.getStringExtra("memberId").toString()

        val memberDAO = MemberDAO(this)
        val selectedMember = memberDAO.showDetail(memberId)

        val name = selectedMember.name
        val age = selectedMember.age
        val mobile = selectedMember.mobile

        val editName: EditText = binding.name
        editName.setText(name)
        val editAge: EditText = binding.age
        editAge.setText(age)
        val editMobile: EditText = binding.mobile
        editMobile.setText(mobile)

        val updateBtn: Button = binding.updateBtn
        updateBtn.setOnClickListener {
            val updateMember = Member(
                editName.text.toString(),
                editAge.text.toString(),
                editMobile.text.toString(),
                memberId
            )
            memberDAO.updateMember(updateMember)
            Intent(this, MemberDetailActivity::class.java).apply {
                putExtra("memberId", memberId)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { startActivities(arrayOf(this)) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}