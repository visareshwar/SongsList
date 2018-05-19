package songsinfo.android.vish.com.songsinfo.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import songsinfo.android.vish.com.songsinfo.db.entity.Song
import songsinfo.android.vish.com.songsinfo.repo.SongsDataRepo

class SongsViewModel(private val songsRepo: SongsDataRepo) : ViewModel() {

    fun getSongList(queryStr: String): LiveData<List<Song>> {
        return songsRepo.getSongsList(queryStr)
    }

}