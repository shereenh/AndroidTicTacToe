package shereen.game.tictactoe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import shereen.game.tictactoe.model.DataRepository
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.room.TDatabase

class SViewModel(application: Application): MainViewModel(application) {

//    fun setShowDialogLive(value: Int){
//        mRepository.showDialog.value = value
//    }
//
//    fun retrieveShowDialogLive(): MutableLiveData<Int> {
//        return mRepository.showDialog
//    }

    fun setDialogHide(){
        mRepository.showDialog.value = false
    }
}