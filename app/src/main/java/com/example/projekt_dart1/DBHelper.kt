package com.example.projekt_dart1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + NAME_COL + " TEXT, "
                + ROUNDS_COL + " INTEGER, "
                + THROWS_COL + " INTEGER" + ")")
        db.execSQL(query)
        Log.d("DBHelper", "Table created with query: $query")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(name: String, rounds: Int, throws: Int) {
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(ROUNDS_COL, rounds)
        values.put(THROWS_COL, throws)

        val db = this.writableDatabase



        val result = db.insert(TABLE_NAME, null, values)

        if (result == -1L) {
            Log.e("DBHelper", "Failed to insert data")
        } else {
            Log.d("DBHelper", "Data inserted successfully: $name, $rounds, $throws")
        }
        db.close()
    }

    fun getData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object {
        private const val DATABASE_NAME = "WINNERS.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "winners_table"
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val ROUNDS_COL = "rounds"
        const val THROWS_COL = "throws"
    }
}