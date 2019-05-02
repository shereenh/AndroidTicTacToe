package shereen.game.tictactoe.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_settings.view.*
import shereen.game.tictactoe.R

class SettingsDialog : DialogFragment() {

    lateinit var rootView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.dialog_settings, container, false)

        rootView.doneButton.setOnClickListener{

        }

        return rootView
    }
}