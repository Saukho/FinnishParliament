package finnish.parliament.data.repository
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import androidx.lifecycle.LiveData
import finnish.parliament.MyApp
import finnish.parliament.data.database.CommentsDatabase
import finnish.parliament.data.model.Comments

object CommentsRepository {
    private val commentsDAO = CommentsDatabase.getInstance(MyApp.appContext).commentsDAO

    val commentData: LiveData<List<Comments>> = commentsDAO.getComments()

    suspend fun addComment(comments: Comments){
       commentsDAO.addComments(comments)
    }
}