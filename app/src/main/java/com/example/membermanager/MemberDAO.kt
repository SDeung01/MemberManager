package com.example.membermanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class MemberDAO(context:Context) {
    val db: SQLiteDatabase = MemberDBHelper(context).writableDatabase
    val TABLE: String = "MEMBER_TB"

    fun addMember(member: Member) {
        db.insert(TABLE, null, member.values())
    }

    fun updateMember(member: Member) {
        db.update(TABLE, member.values(), "member_id=?", arrayOf(member.memberId))
    }

    fun deleteMember(memberId: String) {
        db.delete(TABLE, "member_id=?", arrayOf(memberId))
    }

    fun showDetail(memberId: String): Member {
        var member: Member? = null
        val cursor = db.rawQuery("select * from $TABLE where member_id = $memberId", null)
        if(cursor.moveToFirst()){
            val name = cursor.getString(1)
            val age = cursor.getString(2)
            val mobile = cursor.getString(3)
            val memberId = cursor.getString(0)
            member = Member(name, age, mobile, memberId)
        }
        return member!!
    }

    fun showMemberList(): MutableList<Member> {
        val datas: MutableList<Member> = mutableListOf()
        val cursor = db.rawQuery("select * from $TABLE", null)
        cursor.run {
            while (moveToNext()){
                val name = cursor.getString(1)
                val age = cursor.getString(2)
                val mobile = cursor.getString(3)
                val memberId = cursor.getString(0)
                val member = Member(name, age, mobile, memberId)
                datas.add(member)
            }
        }
        return datas
    }

    fun close(){
        if(db.isOpen){
            db.close()
        }
    }
}