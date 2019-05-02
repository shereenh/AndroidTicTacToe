package shereen.game.tictactoe.model.entity

class GameEntity(){
    var counter: Int = 0
    var isFirstPlayer: Boolean = true
    var isGameDone: Boolean = false
    var level = 1
    var status: String = "started"
    var boardView: BoardView = BoardView()
    var board = ArrayList<Int>()

    init {
        repeat(9){
            board.add(0)
        }
    }
}