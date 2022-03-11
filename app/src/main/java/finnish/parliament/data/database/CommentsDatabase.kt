package finnish.parliament.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import finnish.parliament.MyApp
import finnish.parliament.data.model.Comments

//create database
@Database(entities = [Comments::class],
          version =1, exportSchema = false)
abstract class CommentsDatabase: RoomDatabase() {
    abstract val commentsDAO: CommentsDAO

    companion object {
        @Volatile
        private var INSTANCE: CommentsDatabase? = null

        fun getInstance(context: Context): CommentsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        CommentsDatabase::class.java,
                        "comments_db",).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}