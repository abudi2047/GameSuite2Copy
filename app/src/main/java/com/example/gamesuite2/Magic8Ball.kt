package com.example.gamesuite2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import java.util.*

class Magic8BallActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private val answers = arrayOf(
        "Yes!", "No.", "Maybe...", "Ask again later.",
        "Not sure."
    )
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magic_8_ball)

        val ballImageView = findViewById<ImageView>(R.id.ballImageView)
        answerTextView = findViewById<TextView>(R.id.answerTextView)
        val askButton = findViewById<Button>(R.id.askButton)

        askButton.setOnClickListener {
            provideAnswer()
        }
    }

    private fun provideAnswer() {
        val randomAnswer = answers[random.nextInt(answers.size)]
        answerTextView.text = randomAnswer
    }
}
