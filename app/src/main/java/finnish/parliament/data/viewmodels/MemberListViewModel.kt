package finnish.parliament.data.viewmodels
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import android.app.Application
import androidx.lifecycle.*
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.data.repository.MembersRepository

class MemberListViewModel(members: MemberOfParliament, application: Application) :
    AndroidViewModel(application) {
    //initialize repository, livedata and map selected party
    val membersRepository = MembersRepository
    var membersData = membersRepository.memberData
    val selectedParty = Transformations.map(membersData){
        membersData.value?.filter { it.party == members.party }
    }

    private val _selectedMember = MutableLiveData<MemberOfParliament?>()
    val selectedMember: LiveData<MemberOfParliament?>
        get() = _selectedMember

    fun memberDetails(members: MemberOfParliament) {
        _selectedMember.value = members
    }

    fun navigationDone() {
        _selectedMember.value = null
    }
}
@Suppress("UNCHECKED CAST")
class MemberListViewModelFactory(
    private val dataSource : MemberOfParliament,
    private val application: Application
                                ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
            return MemberListViewModel(dataSource , application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

