package shereen.game.tictactoe.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import shereen.game.tictactoe.R
import shereen.game.tictactoe.databinding.FragmentSettingsBinding
import shereen.game.tictactoe.viewmodel.MainViewModel
import shereen.game.tictactoe.viewmodel.SettingsViewModel


class SettingsFragment : Fragment() {

    lateinit var rootView: View
    lateinit var binding: FragmentSettingsBinding
    lateinit var mViewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        rootView = binding.root
        init()
        observers()

        return rootView
    }

    private fun init(){
        mViewModel = activity?.run{ ViewModelProviders.of(this).get(SettingsViewModel::class.java)}?: throw Exception("Invalid Activity") as Throwable
        binding.viewmodel = mViewModel
    }

    private fun observers(){
        mViewModel.settingsChanged.observe(this, Observer<Boolean>{ changed ->
            if(changed){
//                binding.invalidateAll()
                binding.viewmodel = mViewModel
                mViewModel.changeBackSettings()
            }

        })
    }


}
