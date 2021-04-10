package com.cv.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , View.OnClickListener{
    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3){IntArray(3)}

    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

    for(i:Array<Button> in board){
        for(button : Button in i){
            button.setOnClickListener(this)
        }

    }
        initializeBoardStatus()

        reset.setOnClickListener{
            TURN_COUNT = 0
            PLAYER = true
            initializeBoardStatus()

        }

    }

    private fun initializeBoardStatus() {
        for (i: Int in 0..2){
            for(j: Int in 0..2){
                boardStatus[i][j] = -1
            }
        }

        for( i : Array<Button> in board){
            for( button: Button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){

            R.id.button1 ->{
               updateValue(row = 0 , col = 0 , player = PLAYER)
            }
            R.id.button2 ->{
                updateValue(row = 0 , col = 1 , player = PLAYER)

            }
            R.id.button3 ->{
                updateValue(row = 0 , col = 2 , player = PLAYER)

            }
            R.id.button4 ->{
                updateValue(row = 1 , col = 0 , player = PLAYER)

            }
            R.id.button5 ->{
                updateValue(row = 1 , col = 1 , player = PLAYER)

            }
            R.id.button6 ->{
                updateValue(row = 1 , col = 2 , player = PLAYER)

            }
            R.id.button7 ->{
                updateValue(row = 2 , col = 0 , player = PLAYER)

            }
            R.id.button8 ->{
                updateValue(row = 2 , col = 1 , player = PLAYER)

            }
            R.id.button9 ->{
                updateValue(row = 2 , col = 2 , player = PLAYER)

            }

        }
        PLAYER = !PLAYER
        TURN_COUNT++

        if(PLAYER){
            updateDisplay("Player X turn")
        }else{
            updateDisplay("Player O turn")
        }

        if(TURN_COUNT == 9){
            updateDisplay("Match Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {

        for(i : Int in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }

        for(i : Int in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }


        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")

            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player O Winner")

            }
        }

        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")

            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player O Winner")

            }
        }




    }

    private fun updateDisplay(s: String) {
          playerturn.text= s
         if(s.contains("Winner")){
             disableButtons()
         }
    }

    private fun disableButtons() {
        for(i: Array<Button> in board){
            for(button : Button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
             val text: String = if(player) "X" else "O"
             val value: Int = if(player) 1 else 0

            board[row][col].apply {
                isEnabled = false
                setText(text)
            }
        boardStatus[row][col] = value
    }
}