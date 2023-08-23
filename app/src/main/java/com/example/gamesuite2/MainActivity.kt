package com.example.gamesuite2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //
        setContentView(R.layout.activity_main) // Set it to the main activity layout

        val btnTicTacToe: Button = findViewById(R.id.btnTicTacToe)

        btnTicTacToe.setOnClickListener {
            startActivity(Intent(this, TicTacToeActivity::class.java))
        }
        //test for github

        val btnFindThePair = findViewById<Button>(R.id.btnFindThePair)
        btnFindThePair.setOnClickListener {
            val intent = Intent(this, FindThePairActivity::class.java)
            startActivity(intent)
        }

        val btnMagic8Ball: Button = findViewById(R.id.btnMagic8Ball)
        btnMagic8Ball.setOnClickListener {
            startActivity(Intent(this, Magic8BallActivity::class.java))
        }

        // TODO: Implement intent actions for the other games once they are created.
    }
}
