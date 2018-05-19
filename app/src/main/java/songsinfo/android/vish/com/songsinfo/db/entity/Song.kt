package songsinfo.android.vish.com.songsinfo.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Song {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @SerializedName("Name")
    @Expose
    var name: String? = null
    @SerializedName("Artist")
    @Expose
    var artist: String? = null
    @SerializedName("Album")
    @Expose
    var album: String? = null

}