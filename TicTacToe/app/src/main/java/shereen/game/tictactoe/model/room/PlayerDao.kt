package shereen.game.tictactoe.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.entity.PlayerEntity

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: PlayerEntity)

    @Update
    fun updatePlayer(player: PlayerEntity)

    @Query("DELETE FROM " + TConstants.PLAYER_TABLE +" WHERE id!=1 OR id!=2")
    fun deletePlayers()

    @Query("SELECT * FROM " + TConstants.PLAYER_TABLE)
    fun getAllPlayers():LiveData<List<PlayerEntity>>

    @Query("SELECT * FROM " + TConstants.PLAYER_TABLE + " WHERE id=:pid")
    fun getPlayerById(pid: Int):LiveData<PlayerEntity>
}