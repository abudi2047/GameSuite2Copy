package com.example.gamesuite2

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast

class PairAdapter(private val context: Context) : BaseAdapter() {

    // Tiles
    private val tiles = listOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3,
        R.drawable.img4, R.drawable.img5, R.drawable.img6,
        R.drawable.img1, R.drawable.img2, R.drawable.img3,
        R.drawable.img4, R.drawable.img5, R.drawable.img6
    ).shuffled()

    // Variables to track the state of the game
    private var firstClickedPosition: Int? = null
    private val revealedTiles = mutableSetOf<Int>()

    override fun getCount(): Int = tiles.size

    override fun getItem(position: Int): Int = tiles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.pair_tile, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imagePairTile)

        if (position in revealedTiles) {
            imageView.setImageResource(getItem(position))
        } else {
            imageView.setImageResource(R.drawable.img1) // Change this to your actual placeholder image
        }

        view.setOnClickListener {
            handleTileClick(position, imageView)
        }

        return view
    }

    private fun handleTileClick(position: Int, imageView: ImageView) {
        if (firstClickedPosition == null) {
            firstClickedPosition = position
            revealedTiles.add(position)
            notifyDataSetChanged()
        } else {
            revealedTiles.add(position)
            notifyDataSetChanged()
            if (getItem(firstClickedPosition!!) == getItem(position) && firstClickedPosition != position) {
                // It's a pair!
                Toast.makeText(context, "That's a pair!", Toast.LENGTH_SHORT).show()

                if (revealedTiles.size == tiles.size) {
                    // All pairs are found
                    Toast.makeText(context, "All pairs found!", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Not a pair
                Toast.makeText(context, "Not a pair!", Toast.LENGTH_SHORT).show()
                // Hide the tiles after a short delay
                Handler().postDelayed({
                    revealedTiles.remove(firstClickedPosition!!)
                    revealedTiles.remove(position)
                    notifyDataSetChanged()
                }, 1000)  // 1-second delay
            }
            firstClickedPosition = null
        }
    }
}
