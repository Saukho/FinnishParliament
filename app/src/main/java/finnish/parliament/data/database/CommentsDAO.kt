package finnish.parliament.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import finnish.parliament.data.model.Comments

@Dao
interface CommentsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComments(comments: Comments)

    @Query("SELECT * FROM comments_db order by timestamp")
    fun getComments(): LiveData<List<Comments>>
}