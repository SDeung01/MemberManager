package com.example.membermanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MemberDBHelper(context: Context): SQLiteOpenHelper(context, "member", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table MEMBER_TB (" +
                "member_id integer primary key autoincrement," +
                "name not null," +
                "age not null," +
                "mobile not null)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}