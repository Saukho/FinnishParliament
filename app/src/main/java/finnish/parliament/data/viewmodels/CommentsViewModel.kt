package finnish.parliament.data.viewmodels
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.data.repository.CommentsRepository
import java.lang.IllegalArgumentException

class CommentsViewModel(members: MemberOfParliament, application: Application) :
    AndroidViewModel(application) {

    private val commentsList = CommentsRepository.commentData

    val comment = Transformations.map(commentsList) {
        commentsList.value?.filter { it.personNumber == members.personNumber }
    }

class CommentsViewModelFactory(
    private val dataSource: MemberOfParliament,
    private val application: Application
                              ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}}