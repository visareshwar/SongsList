package songsinfo.android.vish.com.songsinfo.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import songsinfo.android.vish.com.songsinfo.db.entity.Song

@Dao
abstract class SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(repos: List<Song>)

    @Query("SELECT * FROM Song WHERE artist COLLATE NOCASE like :queryStr or album COLLATE NOCASE like :queryStr")
    abstract fun findByArtist(queryStr: String): LiveData<List<Song>>


    @Query("SELECT * FROM Song")
    abstract fun getAllSongs(): LiveData<List<Song>>
}