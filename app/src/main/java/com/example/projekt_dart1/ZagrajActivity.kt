package com.example.projekt_dart1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ZagrajActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_zagraj)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val jedengracz = findViewById<Button>(R.id.gracz1)
        val dwagracz = findViewById<Button>(R.id.gracz2)
        val trzygracz = findViewById<Button>(R.id.gracz3)
        val czterygracz = findViewById<Button>(R.id.gracz4)
        val powrot = findViewById<Button>(R.id.powrot)
        val grajdalej = findViewById<Button>(R.id.graj)
        val jedenobrazek = findViewById<ImageView>(R.id.imageFilterView2)
        val dwaobrazek = findViewById<ImageView>(R.id.imageFilterView3)
        val trzyobrazek = findViewById<ImageView>(R.id.imageFilterView4)
        val czteryobrazek = findViewById<ImageView>(R.id.imageFilterView5)
        val jedennick = findViewById<EditText>(R.id.nick1)
        val dwanick = findViewById<EditText>(R.id.nick2)
        val trzynick = findViewById<EditText>(R.id.nick3)
        val czterynick = findViewById<EditText>(R.id.nick4)
        val podajnicki = findViewById<TextView>(R.id.textView4)
        var liczba_graczy = 0


        jedengracz.setOnClickListener {
            jedenobrazek.visibility = ImageView.VISIBLE
            dwaobrazek.visibility = ImageView.INVISIBLE
            trzyobrazek.visibility = ImageView.INVISIBLE
            czteryobrazek.visibility = ImageView.INVISIBLE
            jedennick.visibility = EditText.VISIBLE
            dwanick.visibility = EditText.INVISIBLE
            trzynick.visibility = EditText.INVISIBLE
            czterynick.visibility = EditText.INVISIBLE
            podajnicki.visibility = TextView.VISIBLE
            liczba_graczy = 1
        }

        dwagracz.setOnClickListener {
            jedenobrazek.visibility = ImageView.INVISIBLE
            dwaobrazek.visibility = ImageView.VISIBLE
            trzyobrazek.visibility = ImageView.INVISIBLE
            czteryobrazek.visibility = ImageView.INVISIBLE
            jedennick.visibility = EditText.VISIBLE
            dwanick.visibility = EditText.VISIBLE
            trzynick.visibility = EditText.INVISIBLE
            czterynick.visibility = EditText.INVISIBLE
            podajnicki.visibility = TextView.VISIBLE
            liczba_graczy = 2
        }
        trzygracz.setOnClickListener {
            jedenobrazek.visibility = ImageView.INVISIBLE
            dwaobrazek.visibility = ImageView.INVISIBLE
            trzyobrazek.visibility = ImageView.VISIBLE
            czteryobrazek.visibility = ImageView.INVISIBLE
            jedennick.visibility = EditText.VISIBLE
            dwanick.visibility = EditText.VISIBLE
            trzynick.visibility = EditText.VISIBLE
            czterynick.visibility = EditText.INVISIBLE
            podajnicki.visibility = TextView.VISIBLE
            liczba_graczy = 3
        }
        czterygracz.setOnClickListener {
            jedenobrazek.visibility = ImageView.INVISIBLE
            dwaobrazek.visibility = ImageView.INVISIBLE
            trzyobrazek.visibility = ImageView.INVISIBLE
            czteryobrazek.visibility = ImageView.VISIBLE
            jedennick.visibility = EditText.VISIBLE
            dwanick.visibility = EditText.VISIBLE
            trzynick.visibility = EditText.VISIBLE
            czterynick.visibility = EditText.VISIBLE
            podajnicki.visibility = TextView.VISIBLE
            liczba_graczy = 4
        }
        powrot.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        grajdalej.setOnClickListener {
            if (liczba_graczy!=0) {
                val intent = Intent(this, GameActivity::class.java).apply {
                    putExtra("LICZBA_GRACZY", liczba_graczy)
                    putExtra("GRACZ1", jedennick.text.toString())
                    putExtra("GRACZ2", dwanick.text.toString())
                    putExtra("GRACZ3", trzynick.text.toString())
                    putExtra("GRACZ4", czterynick.text.toString())
                }
                startActivity(intent)
            }
        }
    }
}