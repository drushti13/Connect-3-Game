package com.example.connect3game

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class MainActivity : AppCompatActivity() {
    //0:blue 1:blue 2:empty
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    var activePlayer = 0
    var gameActive = true
    fun dropIn(view: View) {
        val counter = view as ImageView
        Log.i("TAG", counter.tag.toString())
        val tappedCounter = counter.tag.toString().toInt()
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer
            counter.translationY = -1500f
            activePlayer = if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red)
                1
            } else {
                counter.setImageResource(R.drawable.blue)
                0
            }
            counter.animate().translationYBy(1500f).setDuration(300)
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone won
                    gameActive = false
                    var winner = ""
                    winner = if (activePlayer == 0) {
                        "BLUE"
                    } else {
                        "RED"
                    }
                    val button = findViewById<View>(R.id.button) as Button
                    val textView = findViewById<View>(R.id.textView) as TextView
                    textView.text = "$winner won!!"
                    button.visibility = View.VISIBLE
                    textView.visibility = View.VISIBLE
                }
            }
        }
    }

    fun playAgain(view: View?) {
        Log.i("info", "pressed")
        val button = findViewById<View>(R.id.button) as Button
        val textView = findViewById<View>(R.id.textView) as TextView
        button.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE
        val gridLayout = findViewById<View>(R.id.gridLayout) as GridLayout
        for (i in 0 until gridLayout.childCount) {
            val counter = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
        }
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        activePlayer = 0
        gameActive = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}