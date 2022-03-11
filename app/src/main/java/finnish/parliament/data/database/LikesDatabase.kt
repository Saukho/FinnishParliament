package finnish.parliament.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import finnish.parliament.MyApp
import finnish.parliament.data.model.Likes

//create likes database
@Database(entities = [Likes::class],
          version =1, exportSchema = false)
abstract class LikesDatabase: RoomDatabase() {
    abstract val likesDAO: LikesDAO

    companion object {
        @Volatile
        private var INSTANCE: LikesDatabase? = null

        fun getLikes(context: Context): LikesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        LikesDatabase::class.java,
                        "likes_db",).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}