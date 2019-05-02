package shereen.game.tictactoe.viewmodel

import android.app.Application
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import shereen.game.tictactoe.R
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.entity.GameEntity
import shereen.game.tictactoe.model.entity.WinEntity
import shereen.game.tictactoe.view.helper.GameHelper

class GameViewModel(application: Application): MainViewModel(application) {

    lateinit var game: GameEntity
    var currentPlayer = MutableLiveData<String>()

    var computerTurn = false
    var gameLevel = 1

//    private val beepOne = MediaPlayer.create(application, R.raw.sound_1)
//    private val beepTwo = MediaPlayer.create(application, R.raw.sound_2)

    init {
        newGame()
    }

    private fun markRed(win: WinEntity){
        if(isFirstPlayer.value!!){
            game.boardView.imageShown[win.index1!!] = R.drawable.o_win
            game.boardView.imageShown[win.index2!!] = R.drawable.o_win
            game.boardView.imageShown[win.index3!!] = R.drawable.o_win
        }else{
            game.boardView.imageShown[win.index1!!] = R.drawable.x_win
            game.boardView.imageShown[win.index2!!] = R.drawable.x_win
            game.boardView.imageShown[win.index3!!] = R.drawable.x_win
        }
    }

    fun newGame(){
        game = GameEntity()
        enableButtons()
        isFirstPlayer.value = game.isFirstPlayer
        currentPlayer.value = "Player 1"
        gameStatus.value = game.status
        game.level = gameLevel
        clearBoard.value = false
        gameChanged.value = false
    }

    fun playTurn(index: Int){
        game.boardView.buttonVis[index] = View.GONE

        if(gameMode.value == TConstants.S_MODE){
            singleDeviceTurn(index)
        }else if(gameMode.value == TConstants.C_MODE){
            computerModePlayerTurn(index)
        }
        updateGame()

        if(gameMode.value == TConstants.C_MODE && game.counter < 9 && gameStatus.value == "started" && computerTurn){
            Handler().postDelayed({
                computerModeComputerTurn()
                updateGame()
            }, 300)
        }
    }

    private fun updateGame(){
        val win = GameHelper.checkWin(game.board)
        if(win.isWin){
            disableButtons()
            computerTurn = false
            markRed(win)
            if(isFirstPlayer.value!!){
                val score = playerScore1.value!!.toInt()
                mRepository.changeScoreA((score+1).toString())
                gameStatus.value = "player 1"
            }else{
                val score = playerScore2.value!!.toInt()
                mRepository.changeScoreB((score+1).toString())
                gameStatus.value = "player 2"
            }
        }else{
            isFirstPlayer.value = !(isFirstPlayer.value)!!
            game.counter++

            if(game.counter==9){
                gameStatus.value = "game over"
            }
        }
        Log.d(TConstants.LOGGER, gameStatus.value)
    }

    private fun singleDeviceTurn(index: Int){
        if(isFirstPlayer.value!!){
//            beepOne.start()
            game.boardView.imageShown[index] = TConstants.O
            game.board[index] = 1
            currentPlayer.value = "Player 2"
        }else{
//            beepTwo.start()
            game.boardView.imageShown[index] = TConstants.X
            game.board[index] = -1
            currentPlayer.value = "Player 1"
        }
        gameChanged.value = true
    }

    /************************************** COMPUTER MODE **********************************/

    fun computerModePlayerTurn(index: Int){
        // play user's move
//        beepOne.start()

        game.boardView.imageShown[index] = TConstants.O
        game.board[index] = 1
        currentPlayer.value = "Player 2"
        disableButtons()
        computerTurn = true
        gameChanged.value = true
    }

    fun computerModeComputerTurn(){
        //play computer's move after 2 sec
//        Handler().postDelayed({
//            beepTwo.start()
//        }, 200)
        val computerIndex = GameHelper.checkPossibleMove(game.board, -1, game.level)
        game.boardView.buttonVis[computerIndex] = View.GONE
        game.boardView.imageShown[computerIndex] = TConstants.X
        game.board[computerIndex] = -1
        currentPlayer.value = "Player 1"
        enableButtons()
        computerTurn = false
        gameChanged.value = true
    }

    private fun disableButtons(){
        for(x in 0..8){
            game.boardView.buttonEnabled[x] = false
        }
    }

    private fun enableButtons(){
        for(x in 0..8){
            game.boardView.buttonEnabled[x] = true
        }
    }

    fun changeLevel(level: Int){
        gameLevel = level
        game.level = gameLevel
//        clearGameBoard()
        gameChanged.value = true
    }
}