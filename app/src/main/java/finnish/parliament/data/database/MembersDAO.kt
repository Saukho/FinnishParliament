package finnish.parliament.data.database
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* Dao for insertOrUpdate or filter data from database
 */
import androidx.lifecycle.LiveData
import androidx.room.*
import finnish.parliament.data.model.MemberOfParliament


/*setting dao interface*/
@Dao
interface MembersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(memberOfParliament: MemberOfParliament)

    @Query("SELECT * FROM members_db")
    fun getAllMembers(): LiveData<List<MemberOfParliament>>
}