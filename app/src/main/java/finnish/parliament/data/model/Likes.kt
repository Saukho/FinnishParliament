package finnish.parliament.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@Parcelize
@Entity(tableName = "likes_db")
data class Likes(
    @PrimaryKey(autoGenerate = true)
    val personNumber: Int,
    val likes: Int,
    val timestamp: String
                ): Parcelable