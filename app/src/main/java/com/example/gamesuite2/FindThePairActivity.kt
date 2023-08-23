package com.example.gamesuite2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
//added comment
class FindThePairActivity : AppCompatActivity() {

    private lateinit var adapter: PairAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_the_pair)

        adapter = PairAdapter(this)
        val gridView = findViewById<GridView>(R.id.gridViewPairs)
        gridView.adapter = adapter
    }
}
