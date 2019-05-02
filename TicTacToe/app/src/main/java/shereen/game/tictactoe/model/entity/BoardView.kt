package shereen.game.tictactoe.model.entity

import android.view.View
import shereen.game.tictactoe.R

class BoardView {

    var buttonVis = arrayListOf(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                                View.VISIBLE, View.VISIBLE, View.VISIBLE,
                                View.VISIBLE, View.VISIBLE, View.VISIBLE)

    var buttonEnabled = arrayListOf(true, true, true,
                                    true, true, true,
                                    true, true, true)

    var imageShown = arrayListOf(R.drawable.o, R.drawable.o, R.drawable.o,
                                 R.drawable.o, R.drawable.o, R.drawable.o,
                                 R.drawable.o1, R.drawable.o1, R.drawable.o1)
}