package shereen.game.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import shereen.game.tictactoe.viewmodel.MainViewModel
import shereen.game.tictactoe.R
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.view.fragments.PlayFragment
import shereen.game.tictactoe.view.fragments.SettingsDialog
import shereen.game.tictactoe.view.fragments.SettingsFragment
import shereen.game.tictactoe.viewmodel.GameViewModel
import shereen.game.tictactoe.viewmodel.SViewModel
import shereen.game.tictactoe.viewmodel.StatusViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mStatusViewModel: StatusViewModel
    lateinit var mGameModel: GameViewModel
    lateinit var mSettingsViewModel: SViewModel

    lateinit var playFragment: PlayFragment
    lateinit var settingsFragment: SettingsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observers()
    }

    private fun init(){
        mStatusViewModel = ViewModelProviders.of(this).get(StatusViewModel::class.java)
        mGameModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        mSettingsViewModel = ViewModelProviders.of(this).get(SViewModel::class.java)

        playFragment = PlayFragment()
        settingsFragment = SettingsFragment()

        supportFragmentManager.inTransaction {
            replace(R.id.frameLayout, playFragment)
        }

    }

    private fun observers(){

        mGameModel.gameStatus.observe(this, Observer<String> { status ->
            when (status) {
                "player 1" -> gameDone(getString(R.string.player_1_wins))
                //                mMainModel.showDialog(getString(R.string.player_1_wins), this)
                "player 2" -> gameDone(getString(R.string.player_2_wins))
                //                mMainModel.showDialog(getString(R.string.player_2_wins), this)
                "game over" -> gameDone(getString(R.string.draw))
                //                mMainModel.showDialog(getString(R.string.game_over), this)
            }
        })

        mGameModel.clearBoard.observe(this, Observer<Boolean> { clearBoard ->
            if(clearBoard){
                newGameBoard()
            }
        })

        mStatusViewModel.showDial.observe(this, Observer<Boolean> { showDial ->
            if(showDial){
                showDialog()
                mStatusViewModel.showDial.value = false
            }
        })

//        mStatusViewModel.retr.observe(this, Observer<Int> { showVal ->
//            when(showVal){
//                1 -> {
//                    showDialog()
//                    mStatusViewModel.showDialogLive.value = 0
//                }
//            }
//
//        })

        mSettingsViewModel.showDial.observe(this, Observer<Boolean> { show ->
            if(show){
                showDialog()
                dialogLayout.visibility = View.VISIBLE
            }else{
                dialogLayout.visibility = View.GONE
            }
        })

    }

    fun newGameBoard(){
        playFragment = PlayFragment()
        supportFragmentManager.inTransaction {
            replace(R.id.frameLayout, playFragment)
        }
        mGameModel.newGame()
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.top_menu, menu)
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
////        return super.onOptionsItemSelected(item)
//        when(item!!.itemId){
//            R.id.reset_score-> { mStatusViewModel.resetScore()}
//            R.id.settings -> {
//                supportFragmentManager.inTransaction {
//                replace(R.id.frameLayout, settingsFragment)
//                }
//            }
//            R.id.gameBoard -> {
//                newGameBoard()
//            }
//
//        }
//        return true
//    }

    fun showDialog(){
        dialogLayout.visibility = View.VISIBLE
        supportFragmentManager.inTransaction {
            replace(R.id.dialogLayout, SettingsDialog())
        }
    }

    fun gameDone(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        newGameBoard()

        Handler().postDelayed({
            newGameBoard()
        }, 1000)
    }

}
