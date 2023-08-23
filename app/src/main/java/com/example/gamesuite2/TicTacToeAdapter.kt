package com.example.gamesuite2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
//import com.example.gamesuite.R

class TicTacToeAdapter(
    private val context: Context,
    private val nameCheck: () -> Boolean
) : BaseAdapter() {

    // Change to a nullable grid
    private var grid = Array(3) { arrayOfNulls<String>(3) }

    override fun getCount(): Int = 9

    override fun getItem(position: Int): String? {
        val row = position / 3
        val col = position % 3
        return grid[row][col]
    }

    fun resetGrid() {
        grid = Array(3) { arrayOfNulls<String>(3) } // Use null values to represent empty cells
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.tic_tac_toe_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.ticTacToeItemText)
        textView.text = getItem(position) ?: ""

        return view
    }

    fun setValueAtPosition(position: Int, value: String): Boolean {
        if (nameCheck.invoke()) {
            val row = position / 3
            val col = position % 3
            if (grid[row][col] == null) { // Check if the cell is empty before setting a value
                grid[row][col] = value
                notifyDataSetChanged()
                return true
            }
        }
        return false
    }

    // Here's the getGrid function
    fun getGrid(): Array<Array<String?>> = grid
}
