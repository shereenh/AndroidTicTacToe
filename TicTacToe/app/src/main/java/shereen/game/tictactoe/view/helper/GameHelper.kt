package shereen.game.tictactoe.view.helper

import android.util.Log
import shereen.game.tictactoe.model.entity.WinEntity
import kotlin.random.Random

object GameHelper {

    var board = arrayListOf<Int>()

    //Computer Mode

    fun getRandomMove(gameBoard: ArrayList<Int>, level: Int):Int{
        board = gameBoard
        //check if random exists in table
        if(level >= 2){
            if(gameBoard[4] == 0){
                return 4
            }
        }

        if(level >= 3){
            val setCorners = listOf(0,2,6,8)
            val corner = hashSetOf<Int>()
            while(corner.size != 4){
                val cornerMove = setCorners.random()
                if(gameBoard[cornerMove] == 0){
                    return cornerMove
                }else{
                    corner.add(cornerMove)
                }
            }
        }

        var move = (0..8).random()
        while(gameBoard[move] != 0){
            move = (0..8).random()
        }
        return move
    }

    fun checkPossibleMove(gameBoard: ArrayList<Int>, computer: Int, level: Int):Int{
        board = gameBoard
        var move: Int
        if((level == 1 && Random.nextBoolean()) || level > 1){
            Log.d("Gamer", "win: true")
            move = checkPossibleWin(computer)  // check if win is possible
            if(move != -1){
                return move
            }
        }else{
            Log.d("Gamer", "win: false")
        }

        if((level == 1 && Random.nextBoolean()) || level > 1){
            Log.d("Gamer", "defence: true")
            move = checkPossibleWin(computer * -1) // check if opponent will win
            if(move != -1){
                return move
            }
        }else{
            Log.d("Gamer", "defence: false")
        }

        return getRandomMove(gameBoard, level)

    }

    private fun checkPossibleWin(computer: Int): Int{
        var move = horizontalWin(computer)
        if(move != -1){
            return move
        }
        move = verticalWin(computer)
        if(move != -1){
            return move
        }
        move = diagonalWin(computer)
        return move
    }

    private fun horizontalWin(computer: Int):Int{

        var result = -1

        when {
            board[0] + board[1] + board[2] == computer * 2 -> {
                result = when {
                    board[0] == 0 -> 0
                    board[1] == 0 -> 1
                    else -> 2
                }
            }
            board[3] + board[4] + board[5] == computer * 2 -> {
                result = when {
                    board[3] == 0 -> 3
                    board[4] == 0 -> 4
                    else -> 5
                }
            }
            board[6] + board[7] + board[8] == computer *2 -> {
                result = when {
                    board[6] == 0 -> 6
                    board[7] == 0 -> 7
                    else -> 8
                }
            }
        }
        return result
    }

    private fun verticalWin(computer: Int):Int{

        var result = -1

        when {
            board[0] + board[3] + board[6] == computer * 2 -> {
                result = when {
                    board[0] == 0 -> 0
                    board[3] == 0 -> 3
                    else -> 6
                }
            }
            board[1] + board[4] + board[7] == computer * 2 -> {
                result = when {
                    board[1] == 0 -> 1
                    board[4] == 0 -> 4
                    else -> 7
                }
            }
            board[2] + board[5] + board[8] == computer *2 -> {
                result = when {
                    board[2] == 0 -> 2
                    board[5] == 0 -> 5
                    else -> 8
                }
            }
        }
        return result
    }

    private fun diagonalWin(computer: Int):Int{

        var result = -1

        when {
            board[0] + board[4] + board[8] == computer * 2 -> {
                result = when {
                    board[0] == 0 -> 0
                    board[4] == 0 -> 4
                    else -> 8
                }
            }
            board[2] + board[4] + board[6] == computer * 2 -> {
                result = when {
                    board[2] == 0 -> 2
                    board[4] == 0 -> 4
                    else -> 6
                }
            }
        }
        return result
    }

    // Single Player Mode

    fun checkWin(gameBoard: ArrayList<Int>): WinEntity{
        board = gameBoard
        var win = horizontalCheck(3)
        if(win.isWin){
            return win
        }
        win = verticalCheck(3)
        if(win.isWin){
            return win
        }
        return diagonalCheck(3)
    }

    private fun horizontalCheck(sum: Int):WinEntity{

        val win = WinEntity()

        if(board[0] + board[1] + board[2] == sum || board[0] + board[1] + board[2] == -1 * sum){
            win.isWin = true
            win.index1 = 0
            win.index2 = 1
            win.index3 = 2
        }else if(board[3] + board[4] + board[5] == sum || board[3] + board[4] + board[5] == -1 * sum){
            win.isWin = true
            win.index1 = 3
            win.index2 = 4
            win.index3 = 5
        }else if(board[6] + board[7] + board[8] == sum || board[6] + board[7] + board[8] == -1 * sum){
            win.isWin = true
            win.index1 = 6
            win.index2 = 7
            win.index3 = 8
        }
        return win
    }

    private fun verticalCheck(sum: Int):WinEntity{

        val win = WinEntity()

        if(board[0] + board[3] + board[6] == sum || board[0] + board[3] + board[6] == -1 * sum){
            win.isWin = true
            win.index1 = 0
            win.index2 = 3
            win.index3 = 6
        }else if(board[1] + board[4] + board[7] == sum || board[1] + board[4] + board[7] == -1 * sum){
            win.isWin = true
            win.index1 = 1
            win.index2 = 4
            win.index3 = 7
        }else if(board[2] + board[5] + board[8] == sum || board[2] + board[5] + board[8] == -1 * sum){
            win.isWin = true
            win.index1 = 2
            win.index2 = 5
            win.index3 = 8
        }
        return win
    }

    private fun diagonalCheck(sum: Int):WinEntity{

        val win = WinEntity()

        if(board[0] + board[4] + board[8] == sum || board[0] + board[4] + board[8] == -1 * sum){
            win.isWin = true
            win.index1 = 0
            win.index2 = 4
            win.index3 = 8
        }else if(board[2] + board[4] + board[6] == sum || board[2] + board[4] + board[6] == -1 * sum){
            win.isWin = true
            win.index1 = 2
            win.index2 = 4
            win.index3 = 6
        }
        return win
    }
}