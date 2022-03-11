package finnish.parliament.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import org.w3c.dom.Comment

/*members of parliament database model with parcelize */
@Parcelize
@Entity(tableName = "members_db")
data class MemberOfParliament(
    @PrimaryKey(autoGenerate = true)
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
                             ): Parcelable