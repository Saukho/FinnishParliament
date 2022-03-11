package finnish.parliament.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@Parcelize
@Entity(tableName = "comments_db")
data class Comments(
    @PrimaryKey(autoGenerate = true)
    val personNumber: Int,
    val comment: String,
    val timestamp: String
                             ): Parcelable