package shereen.game.tictactoe.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import shereen.game.tictactoe.model.TConstants
import shereen.game.tictactoe.model.entity.PlayerEntity

@Database(entities = arrayOf(PlayerEntity::class), version = 1)

abstract class TDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        private var INSTANCE: TDatabase? = null

        fun getInstance(context: Context): TDatabase? {
            if (INSTANCE == null) {
                synchronized(TDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TDatabase::class.java,
                        TConstants.DATABASE_NAME
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}