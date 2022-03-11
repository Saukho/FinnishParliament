package finnish.parliament.data.repository
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import androidx.lifecycle.LiveData
import finnish.parliament.MyApp
import finnish.parliament.data.database.LikesDatabase
import finnish.parliament.data.model.Likes

object LikesRepository {
    private val likesDAO = LikesDatabase.getLikes(MyApp.appContext).likesDAO

    val likesData: LiveData<List<Likes>> = likesDAO.getLikes()

    suspend fun addLikes(likes: Likes){
       likesDAO.addLikes(likes)
    }
}