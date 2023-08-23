package com.example.gamesuite2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import com.example.gamesuite2.databinding.ActivityTicTacToeBinding
import androidx.appcompat.app.AppCompatActivity

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicTacToeBinding
    private lateinit var adapter: TicTacToeAdapter
    private var player1Turn = true
    private var player1Name = ""
    private var player2Name = ""
    private var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiate the adapter first
        adapter = TicTacToeAdapter(this) {
            if (gameStarted) {
                true
            } else {
                binding.txtStatus.text = "Please start the game."
                false
            }
        }

        binding.startGameButton.setOnClickListener {
            if (player1Name.isNotBlank() && player2Name.isNotBlank()) {
                gameStarted = true
                adapter.resetGrid()
                binding.txtStatus.text = "$player1Name's turn"
                binding.gridView.adapter = adapter
            } else {
                binding.txtStatus.text = "Please enter both player names."
            }
        }

        binding.gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (adapter.getGrid()[position / 3][position % 3] == " ") {
                val currentPlayer = if (player1Turn) "X" else "O"
                if (adapter.setValueAtPosition(position, currentPlayer)) {
                    checkForWinner(currentPlayer)
                    player1Turn = !player1Turn
                }
            }
        }

        binding.player1NameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                player1Name = s?.toString() ?: ""
                Log.d("TicTacToe", "Player 1 name changed to: $player1Name")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.player2NameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                player2Name = s?.toString() ?: ""
                Log.d("TicTacToe", "Player 2 name changed to: $player2Name")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun checkForWinner(currentPlayer: String) {
        if (checkWin(currentPlayer)) {
            binding.txtStatus.text = "$currentPlayer Wins!"
        } else if (checkDraw()) {
            binding.txtStatus.text = "It's a Draw!"
        }
    }

    private fun checkWin(currentPlayer: String): Boolean {
        val grid = adapter.getGrid()
        for (i in 0..2) {
            if (grid[i][0] == currentPlayer && grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                return true
            }

            if (grid[0][i] == currentPlayer && grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                return true
            }
        }

        if (grid[0][0] == currentPlayer && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            return true
        }

        if (grid[0][2] == currentPlayer && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
            return true
        }

        return false
    }

    private fun checkDraw(): Boolean {
        val grid = adapter.getGrid()
        for (row in grid) {
            for (cell in row) {
                if (cell == " ") {
                    return false
                }
            }
        }
        return true
    }
}
