package finnish.parliament.data.viewmodels
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import android.app.Application
import androidx.lifecycle.*
import finnish.parliament.R
import finnish.parliament.data.database.CommentsDatabase
import finnish.parliament.data.model.Comments
import finnish.parliament.data.model.Likes
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.data.repository.CommentsRepository
import finnish.parliament.data.repository.LikesRepository
import finnish.parliament.data.repository.MembersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Year

class MemberDetailsViewModel(members: MemberOfParliament, application: Application) :
    AndroidViewModel(application) {

    //initialize Repositories, LiveData, selectedMember
    private val membersRepository = MembersRepository
    private var membersData = membersRepository.memberData
    val selectedMember = membersData.value?.find { it.personNumber == members.personNumber }

    private val commentDAO = CommentsDatabase.getInstance(application).commentsDAO
    private val repository = CommentsRepository
    private val likesRepo = LikesRepository
    fun addComment(comments:Comments){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addComment(comments)
        }
    }

    fun addLikes(likes: Likes){
        viewModelScope.launch(Dispatchers.IO) {
            likesRepo.addLikes(likes)
        }
    }
    fun addDislikes(likes: Likes){
        viewModelScope.launch(Dispatchers.IO) {
            likesRepo.addLikes(likes)
        }
    }
    //Initialize content for fragment
    var fullname :String = "${selectedMember?.first} ${selectedMember?.last}"
    var constituency: String = "${selectedMember?.constituency}"
    val ageNow:String ="${(selectedMember?.bornYear.toString().toInt() - Year.now().value)}"
    var age: String = "Age " + ageNow.replaceFirstChar { "" }
    // Setting the name of the party
    val  party =
        when (selectedMember?.party) {
            "ps" ->  "Perussuomalaiset"
            "vihr"-> "Vihreä liitto"
            "sd" -> "Suomen Sosialidemokraattinen Puolue"
            "kesk" -> "Suomen Keskusta"
            "kok" -> "Kansallinen Kokoomus"
            "vas" -> "Vasemmistoliitto"
            "r" -> "Suomen ruotsalainen kansanpuolue"
            "kd" -> "Suomen Kristillisdemokraatit"
            "liik"-> "Liike Nyt"
        else -> "Valta kuuluu kansalle"
        }
        val partyImage =
        when (selectedMember?.party) {
            "ps" -> R.drawable.perus
            "vihr" -> R.drawable.vihreat
            "sd" -> R.drawable.sdp
            "kesk" -> R.drawable.keskusta
            "kok" -> R.drawable.kokoomus
            "vas" -> R.drawable.vasemmisto
            "r" -> R.drawable.rkp
            "kd" -> R.drawable.kristillis
            "liik" -> R.drawable.liike
            else-> R.drawable.vkk
        }
}
@Suppress("UNCHECKED CAST")
class MemberDetailsViewModelFactory (
    private val dataSource: MemberOfParliament,
    private val application: Application
                                    ) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MemberDetailsViewModel::class.java)){
            return MemberDetailsViewModel(dataSource,application)as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    } }