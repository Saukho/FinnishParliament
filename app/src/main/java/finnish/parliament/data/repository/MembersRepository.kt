package finnish.parliament.data.repository
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* Changes data on database on live
 */

import androidx.lifecycle.LiveData
import finnish.parliament.MyApp
import finnish.parliament.data.api.ParliamentApi
import finnish.parliament.data.database.MembersDatabase
import finnish.parliament.data.model.MemberOfParliament

object MembersRepository {
    // fetch data from database
    private val memberDao = MembersDatabase.getInstance(MyApp.appContext).membersDAO
    //get all members to livedata list from database
    val memberData: LiveData<List<MemberOfParliament>> = memberDao.getAllMembers()
    // Refresh LiveData if any changes from API
    suspend fun refreshData() {
        //get members from parliament API
        val members = ParliamentApi.retrofitService.getMembers()
        //loop all members and insert them into database

        val pMembers = ParliamentApi.retrofitService.getMembers()
        pMembers.forEach { memberDao.insertOrUpdate(it)}
//        for (member in 0..members.size) memberDao.insertOrUpdate(members[member])
    }
}