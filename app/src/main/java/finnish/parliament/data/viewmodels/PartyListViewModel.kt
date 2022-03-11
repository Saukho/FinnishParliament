package finnish.parliament.data.viewmodels
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.data.repository.MembersRepository

class PartyListViewModel : ViewModel() {
    private val _selectedParty = MutableLiveData<MemberOfParliament?>()

    val selectedParty: LiveData<MemberOfParliament?>
        get() = _selectedParty

    private val members = MembersRepository.memberData
    val parties = Transformations.map(members) {
        members.value?.distinctBy { it.party }
    }

    fun partyDetails(members: MemberOfParliament) {
        _selectedParty.value = members
    }
    fun navigationDone() {
        _selectedParty.value = null
    }
}