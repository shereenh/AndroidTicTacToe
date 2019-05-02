package shereen.game.tictactoe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class StatusViewModel(application: Application): MainViewModel(application) {

//    val showDial = MutableLiveData<Boolean>()

    init {
//        showDial.value = false
    }

    fun setDialogShown(){
        mRepository.showDialog.value = true
    }
}