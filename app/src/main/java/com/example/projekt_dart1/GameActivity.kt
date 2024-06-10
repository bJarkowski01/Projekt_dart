package com.example.projekt_dart1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lateinit var Nicks: Array<String>
        lateinit var Points: Array<Int>
        lateinit var Throws: Array<Int>
        lateinit var Rounds: Array<Int>
        var currentPlayerNumber = 0
        var throwNumber = 0

        val playerCount = intent.getIntExtra("LICZBA_GRACZY", 10)
        val rezygnuj = findViewById<Button>(R.id.buttonRezygnuj)
        val potwierdz = findViewById<Button>(R.id.buttonPotwierdz)

        var jedennick = findViewById<TextView>(R.id.textViewPlayer1)
        var dwanick = findViewById<TextView>(R.id.textViewPlayer2)
        var trzynick = findViewById<TextView>(R.id.textViewPlayer3)
        var czterynick = findViewById<TextView>(R.id.textViewPlayer4)
        val jedenPoints = findViewById<TextView>(R.id.textViewScore1)
        val dwaPoints = findViewById<TextView>(R.id.textViewScore2)
        val trzyPoints = findViewById<TextView>(R.id.textViewScore3)
        val czteryPoints = findViewById<TextView>(R.id.textViewScore4)
        val currentPlayer = findViewById<TextView>(R.id.textViewCurrentPlayer)
        val currentPoints = findViewById<TextView>(R.id.textViewCurrentPoints)
        val pointsInput = findViewById<EditText>(R.id.editTextPoints)
        var throwNm = findViewById<TextView>(R.id.textViewThrowNr)
        val radio1 = findViewById<RadioButton>(R.id.radioButtonX1)
        val radio2 = findViewById<RadioButton>(R.id.radioButtonX2)
        val radio3 = findViewById<RadioButton>(R.id.radioButtonX3)

        radio1.isChecked = true

        Nicks = Array(playerCount) { "" }
        Points = Array(playerCount) {501}
        Throws = Array(playerCount) {0}
        Rounds = Array(playerCount) {0}

        Nicks[0] = intent.getStringExtra("GRACZ1").toString()
        jedennick.text = Nicks[0]
        jedenPoints.text = Points[0].toString()
        currentPlayer.text = Nicks[0]
        currentPoints.text = Points[0].toString()
        throwNm.text = (throwNumber + 1).toString()

        if (playerCount > 1) {
            Nicks[1] = intent.getStringExtra("GRACZ2").toString()
            dwanick.text = Nicks[1]
            dwaPoints.text = Points[1].toString()
            dwanick.visibility = EditText.VISIBLE
            dwaPoints.visibility = EditText.VISIBLE
        }
        if (playerCount > 2) {
            Nicks[2] = intent.getStringExtra("GRACZ3").toString()
            trzynick.text = Nicks[2]
            trzyPoints.text = Points[2].toString()
            trzynick.visibility = EditText.VISIBLE
            trzyPoints.visibility = EditText.VISIBLE
        }
        if (playerCount > 3) {
            Nicks[3] = intent.getStringExtra("GRACZ4").toString()
            czterynick.text = Nicks[3]
            czteryPoints.text = Points[3].toString()
            czterynick.visibility = EditText.VISIBLE
            czteryPoints.visibility = EditText.VISIBLE
        }

        potwierdz.setOnClickListener {
            val pointsText = pointsInput.text.toString()
            val points = pointsText.toIntOrNull() ?: 0
            var radioValue: Int = 0
            if(radio1.isChecked)
            {
                radioValue = 1
            }
            else if(radio2.isChecked)
            {
                radioValue = 2
            }
            else if(radio3.isChecked)
            {
                radioValue = 3
            }

            val finalPoints = points * radioValue

            Throws[currentPlayerNumber]++
            if (finalPoints < Points[currentPlayerNumber] && Points[currentPlayerNumber] - finalPoints != 1) {
                Points[currentPlayerNumber] -= finalPoints

                currentPoints.text = Points[currentPlayerNumber].toString()
                when(currentPlayerNumber)
                {
                    0 -> jedenPoints.text = Points[currentPlayerNumber].toString()
                    1 -> dwaPoints.text = Points[currentPlayerNumber].toString()
                    2 -> trzyPoints.text = Points[currentPlayerNumber].toString()
                    3 -> czteryPoints.text = Points[currentPlayerNumber].toString()
                }
                throwNumber++

                if (throwNumber == 3)
                {
                    throwNumber = 0
                    Rounds[currentPlayerNumber]++
                    currentPlayerNumber++
                    if (currentPlayerNumber == playerCount)
                    {
                        currentPlayerNumber = 0
                    }
                }
            } else if (finalPoints == Points[currentPlayerNumber] && radioValue == 2) {
                Rounds[currentPlayerNumber]++
                val intent = Intent(this, WinnerActivity::class.java).apply {
                    putExtra("WINNER", Nicks[currentPlayerNumber])
                    putExtra("ROUNDS", Rounds[currentPlayerNumber])
                    putExtra("THROWS", Throws[currentPlayerNumber])
                }
                startActivity(intent)
            } else
            {
                throwNumber = 0
                Rounds[currentPlayerNumber]++
                currentPlayerNumber++
                if (currentPlayerNumber == playerCount)
                {
                    currentPlayerNumber = 0
                }
            }

            currentPlayer.text = Nicks[currentPlayerNumber]
            currentPoints.text = Points[currentPlayerNumber].toString()
            throwNm.text = (throwNumber + 1).toString()

            pointsInput.text.clear()
            radio1.isChecked = true
        }


        rezygnuj.setOnClickListener {
            val intent = Intent(this, ZagrajActivity::class.java)
            startActivity(intent)
        }
    }
}