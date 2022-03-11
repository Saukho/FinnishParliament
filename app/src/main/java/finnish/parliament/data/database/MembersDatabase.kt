package finnish.parliament.data.database
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* Creates room database for member of parliament model
 */
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import finnish.parliament.MyApp
import finnish.parliament.data.model.MemberOfParliament

//create database
@Database(entities = [MemberOfParliament::class],
          version = 1, exportSchema = false)
abstract class MembersDatabase: RoomDatabase() {
    abstract val membersDAO: MembersDAO

    companion object {
        @Volatile
        private var INSTANCE: MembersDatabase? = null

        fun getInstance(context: Context): MembersDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        MembersDatabase::class.java,
                        "members_db",).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}