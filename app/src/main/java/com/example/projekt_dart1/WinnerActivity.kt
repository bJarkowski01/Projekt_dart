package com.example.projekt_dart1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WinnerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winner)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val winner = findViewById<TextView>(R.id.textViewWinner)
        val rounds = findViewById<TextView>(R.id.textViewRounds)
        val throws = findViewById<TextView>(R.id.textViewThrows)
        val bazadanych = findViewById<Button>(R.id.buttonDodajDoBazy)
        val zakoncz = findViewById<Button>(R.id.buttonZakoncz)

        val winnerName = intent.getStringExtra("WINNER").toString()
        val roundsCount = intent.getIntExtra("ROUNDS", 0)
        val throwsCount = intent.getIntExtra("THROWS", 0)

        winner.text = winnerName
        rounds.text = roundsCount.toString()
        throws.text = throwsCount.toString()


        bazadanych.setOnClickListener {
            val db = DBHelper(this, null)
            db.addData(winnerName, roundsCount, throwsCount)

            Toast.makeText(this, "$winnerName został dodany do listy zwycięzców", Toast.LENGTH_LONG).show()
        }

        zakoncz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
