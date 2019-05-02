package shereen.game.tictactoe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import shereen.game.tictactoe.model.DataRepository
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.room.TDatabase


open abstract class MainViewModel(application: Application): AndroidViewModel(application) {

    val mRepository: DataRepository = DataRepository.getInstance(application.getSharedPreferences(TConstants.PREFS_FILE_NAME, 0), TDatabase.getInstance(application)!!.playerDao())

    var clearBoard = mRepository.clearBoard
    var gameStatus = MutableLiveData<String>()  // started, player 1, player 2, game over
    var gameMode = mRepository.gameMode


    var gameChanged = mRepository.gameChanged
    var isFirstPlayer = mRepository.isFirstPlayer


    var playerA = mRepository.playerA
    var playerB = mRepository.playerB

    var playerScore1 = mRepository.score1
    var playerScore2 = mRepository.score2

    val showDial = mRepository.showDialog

    init {

    }

    /************************************** PLAY FRAGMENT **********************************/

//    fun showDialog(message: String, context: Context){
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle(message)
////        builder.setMessage(message)
//        builder.setCancelable(false)
//        builder.setPositiveButton("New Game"){ _, _ ->
////            clearBoard.value = true
//            clearGameBoard()
//        }
//        val dialog: AlertDialog = builder.create()
//        dialog.show()
//    }

    fun clearGameBoard(){
        clearBoard.value = true
    }

    fun resetScore(){
        mRepository.changeScoreA("0")
        mRepository.changeScoreB("0")
        gameChanged.value = true
    }

    fun changeBackGame(){
        gameChanged.value = false
    }

    //##########################
    fun setShowDialogLive(value: Boolean){
        mRepository.showDialog.value = value
    }

    fun retrieveShowDialogLive(): MutableLiveData<Boolean> {
        return mRepository.showDialog
    }
}