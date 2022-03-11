package finnish.parliament.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import finnish.parliament.data.model.Likes

@Dao
interface LikesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLikes(likes: Likes)

    @Query("SELECT * FROM likes_db order by timestamp")
    fun getLikes(): LiveData<List<Likes>>
}