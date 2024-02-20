package com.example.membermanager

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.membermanager.databinding.ActivityMemberDetailBinding

class MemberDetailActivity : AppCompatActivity() {
    lateinit var memberId :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMemberDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val deleteBtn: Button = binding.deleteBtn
        val listBtn: Button = binding.listBtn

        updateBtn.setOnClickListener {
            Intent(this, UpdateActivity::class.java).apply {
                putExtra("memberId", memberId)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { startActivities(arrayOf(this)) }
        }
        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("삭제하시겠습니까.")
                .setMessage("삭제하시면 복구할 수 없습니다.")
                .setPositiveButton("삭제",
                    DialogInterface.OnClickListener { dialog, id ->
                        memberDAO.deleteMember(memberId)
                        val intent = Intent(this, MemberListActivity::class.java)
                        startActivity(intent)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        }
        listBtn.setOnClickListener {
            val intent = Intent(this, MemberListActivity::class.java)
            startActivity(intent)
        }

        memberDAO.close()
    }
}