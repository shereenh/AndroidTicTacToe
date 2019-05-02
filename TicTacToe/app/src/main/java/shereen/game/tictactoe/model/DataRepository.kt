package shereen.game.tictactoe.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import shereen.game.tictactoe.model.entity.PlayerEntity
import shereen.game.tictactoe.model.room.PlayerDao
import shereen.game.tictactoe.model.room.TDatabase

class DataRepository(private val prefs : SharedPreferences, private val playerDao: PlayerDao) {

//    private var playerDao: PlayerDao = TDatabase.getInstance(mContext)!!.playerDao()

    var allPlayers: LiveData<List<PlayerEntity>> = playerDao.getAllPlayers()
    lateinit var player1: LiveData<PlayerEntity>
    lateinit var player2: LiveData<PlayerEntity>

    var playerA = MutableLiveData<String>()
    var playerB = MutableLiveData<String>()

    var gameMode = MutableLiveData<String>() //TConstants.C_MODE

    var score1 = MutableLiveData<String>()
    var score2 = MutableLiveData<String>()

    var clearBoard = MutableLiveData<Boolean>()

    var isFirstPlayer = MutableLiveData<Boolean>()

    var gameChanged = MutableLiveData<Boolean>()

    var showDialog = MutableLiveData<Boolean>()

    init {
        checkFirstTime()
        playerA.value = getStringSharedPref(TConstants.PLAYER_A)
        playerB.value = getStringSharedPref(TConstants.PLAYER_B)

        gameMode.value = TConstants.C_MODE

        score1.value = getStringSharedPref(TConstants.SCORE_A)
        score2.value = getStringSharedPref(TConstants.SCORE_B)

        clearBoard.value = false

        isFirstPlayer.value = true

        gameChanged.value = false

        showDialog.value = false

    }



    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(prefs : SharedPreferences, playerDao: PlayerDao ): DataRepository{
            return instance ?: synchronized(this) {
                instance ?: DataRepository(prefs, playerDao).also { instance = it }
            }
        }
    }



    private fun checkFirstTime(): Boolean{
        val firstTime =  prefs.getBoolean(TConstants.FIRST_TIME, true)
        if(firstTime){
            putSharedPref(TConstants.FIRST_TIME, false)

            insertPlayer(PlayerEntity(pname = "Player A", mode = TConstants.SINGLE_MODE))
            insertPlayer(PlayerEntity(pname = "Player B", mode = TConstants.SINGLE_MODE))

            putSharedPref(TConstants.PLAYER_1, 1)
            putSharedPref(TConstants.PLAYER_2, 2)

            putSharedPref(TConstants.PLAYER_A, "Player A")
            putSharedPref(TConstants.PLAYER_B, "Player B")

            putSharedPref(TConstants.SCORE_A, "0")
            putSharedPref(TConstants.SCORE_B, "0")

        }

        player1 = playerDao.getPlayerById(getIntSharedPref(TConstants.PLAYER_1))
        player2 = playerDao.getPlayerById(getIntSharedPref(TConstants.PLAYER_2))

        return firstTime
    }

    fun changeScoreA(score: String){
        putSharedPref(TConstants.SCORE_A, score)
    }

    fun changeScoreB(score: String){
        putSharedPref(TConstants.SCORE_B, score)
    }

    private fun getIntSharedPref(key: String):Int{
        return prefs.getInt(key, 0)
    }

    private fun getStringSharedPref(key: String):String{
        return prefs.getString(key,"empty")!!
    }

    fun changeFirstPlayer(name: String){
        putSharedPref(TConstants.PLAYER_A, name)
    }

    fun changeSecondPlayer(name: String){
        putSharedPref(TConstants.PLAYER_B, name)
    }

    private fun putSharedPref(key: String, value: Any){
        if ( key in TConstants.prefList){
            val editor = prefs.edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> {}
                is Float -> { }
                is Boolean -> editor.putBoolean(key, value)
            }
            editor.apply()
            postLiveData(key)
        }
    }

    private fun postLiveData(key: String){
        when(key){
            TConstants.PLAYER_1 -> player1 = playerDao.getPlayerById(getIntSharedPref(TConstants.PLAYER_1))
            TConstants.PLAYER_2 -> player2 = playerDao.getPlayerById(getIntSharedPref(TConstants.PLAYER_2))
            TConstants.PLAYER_A -> playerA.value = getStringSharedPref(TConstants.PLAYER_A)
            TConstants.PLAYER_B -> playerB.value = getStringSharedPref(TConstants.PLAYER_B)
            TConstants.SCORE_A -> score1.value = getStringSharedPref(TConstants.SCORE_A)
            TConstants.SCORE_B -> score2.value = getStringSharedPref(TConstants.SCORE_B)
            else -> Log.d(TConstants.LOGGER, "postLiveData nothing matched")
        }
    }

    private fun insertPlayer(player: PlayerEntity){
        GlobalScope.launch{
            val query = async(Dispatchers.IO){
                playerDao.insertPlayer(player)
            }
            query.await()
        }
    }


}