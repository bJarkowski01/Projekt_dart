package com.example.projekt_dart1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TableActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_table)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val przyciskPowrot = findViewById<Button>(R.id.buttonPowrot)
        val dane = findViewById<TextView>(R.id.textViewDaneZBazy)

        val db = DBHelper(this, null)
        val cursor = db.getData()

        if (cursor != null && cursor.moveToFirst()) {
            val stringBuilder = StringBuilder()
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COL))
                val rounds = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.ROUNDS_COL))
                val throws = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.THROWS_COL))

                stringBuilder.append("$name - Rundy: $rounds, Rzuty: $throws\n")
            } while (cursor.moveToNext())
            stringBuilder.append("DOŁĄCZ DO ZWYCIĘZCÓW!")
            dane.text = stringBuilder.toString()

            cursor.close()
        }

        przyciskPowrot.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}