package shereen.game.tictactoe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import shereen.game.tictactoe.model.DataRepository
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.entity.SettingsView

class SettingsViewModel(application: Application): MainViewModel(application) {


    var settingsLive =  MutableLiveData<SettingsView>()
    var settingsChanged = MutableLiveData<Boolean>()

    init {
        settingsChanged.value = false
        settingsLive.value = SettingsView()
    }

    fun changeBackSettings(){
        settingsChanged.value = false
    }

    fun singleMode(){
        val settings = settingsLive.value
        settings!!.singleButtonEnabled = false
        settings.computerButtonEnabled = true
        settingsChanged.value = true
        gameMode.value = TConstants.S_MODE
        resetScore()
    }

    fun computerMode(){
        val settings = settingsLive.value
        settings!!.singleButtonEnabled = true
        settings.computerButtonEnabled = false
        settingsChanged.value = true
        gameMode.value = TConstants.C_MODE
        resetScore()
    }

    fun editFirstPlayer(){
        val settings = settingsLive.value
        if(settings!!.editableTextView1){
            settings.editableTextView1 = false
            mRepository.changeFirstPlayer(playerA.value!!)
        }else{
            settings.editableTextView1 = true
        }
        settingsLive.value = settings
        settingsChanged.value = true
    }

    fun editSecondPlayer(){
        val settings = settingsLive.value
        if(settings!!.editableTextView2){
            settings.editableTextView2 = false
            mRepository.changeSecondPlayer(playerB.value!!)
        }else{
            settings.editableTextView2 = true
        }
        settingsLive.value = settings
        settingsChanged.value = true
    }


}