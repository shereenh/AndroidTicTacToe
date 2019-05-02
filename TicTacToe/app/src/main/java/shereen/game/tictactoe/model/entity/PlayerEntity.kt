package shereen.game.tictactoe.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import shereen.game.tictactoe.model.TConstants

@Entity(tableName = TConstants.PLAYER_TABLE)
class PlayerEntity(@PrimaryKey(autoGenerate = true) var id: Long,
                   @ColumnInfo(name= TConstants.PLAYER_NAME) var pname: String,
                   @ColumnInfo(name= TConstants.PLAYER_MODE) var mode: Int,
                   @ColumnInfo(name= TConstants.PLAYER_SCORE) var score: Int){

    @Ignore
    constructor(pname: String, mode: Int): this(0, pname, mode, 0)
}