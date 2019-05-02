package shereen.game.tictactoe.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import shereen.game.tictactoe.R
import shereen.game.tictactoe.databinding.FragmentPlayBinding
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.viewmodel.GameViewModel
import shereen.game.tictactoe.viewmodel.StatusViewModel


class PlayFragment : Fragment() {

    lateinit var rootView: View
    lateinit var binding: FragmentPlayBinding
    lateinit var mStatusModel: StatusViewModel
    lateinit var mGameModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)
        rootView = binding.root
        init()
        observers()
        return rootView
    }

    private fun init(){
        mStatusModel = activity?.run{ ViewModelProviders.of(this).get(StatusViewModel::class.java)}?: throw Exception("Invalid Activity") as Throwable
        mGameModel  = activity?.run{ ViewModelProviders.of(this).get(GameViewModel::class.java)}?: throw Exception("Invalid Activity") as Throwable
//        binding.lifecycleOwner = this
        binding.viewmodel = mStatusModel
        binding.gamemodel = mGameModel
    }

    private fun observers(){
        mGameModel.gameChanged.observe(this, Observer<Boolean>{ changed ->
            if(changed){
//                binding.invalidateAll()
                binding.gamemodel = mGameModel
                binding.viewmodel = mStatusModel
                mGameModel.changeBackGame()
            }
        })
    }

}
