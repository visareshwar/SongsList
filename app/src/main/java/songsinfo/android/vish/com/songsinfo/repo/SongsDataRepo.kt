package songsinfo.android.vish.com.songsinfo.repo

import android.arch.lifecycle.LiveData
import songsinfo.android.vish.com.songsinfo.AppExecutor
import songsinfo.android.vish.com.songsinfo.db.dao.SongDao
import songsinfo.android.vish.com.songsinfo.db.entity.Song
import songsinfo.android.vish.com.songsinfo.utilities.InjectionUtil

class SongsDataRepo {

    lateinit var songsDao: SongDao

    lateinit var songNetworkRepo: SongNetworkRepo

    fun startSongNetworkRepo(executor: AppExecutor) {
        val data = songNetworkRepo.currentSongsData
        data.observeForever({ songsList ->
            executor.diskIO().execute({
                if (songsList != null)
                    songsDao.insert(songsList)
            })
        })
    }

    fun getSongsList(queryStr: String): LiveData<List<Song>> {
        initializeData()
        return songsDao.findByArtist("%$queryStr%")
    }

    private fun initializeData() {
        if (isFetchNeeded()) {
            songNetworkRepo.fetchDataFromServer()
            InjectionUtil.setFetchNeeded()
        }
    }

    private fun isFetchNeeded(): Boolean {
        return InjectionUtil.isFetchNeeded()
    }


    companion object {

        @Volatile
        private var INSTANCE: SongsDataRepo? = null

        fun getInstance(songDao: SongDao, songNetworkRepo: SongNetworkRepo, executor: AppExecutor): SongsDataRepo =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildSongsRepo(songDao, songNetworkRepo, executor).also { INSTANCE = it }
                }

        private fun buildSongsRepo(songsDao: SongDao, songNetworkRepo: SongNetworkRepo, executor: AppExecutor): SongsDataRepo {
            val instance = SongsDataRepo()
            instance.songsDao = songsDao
            instance.songNetworkRepo = songNetworkRepo
            instance.startSongNetworkRepo(executor)
            return instance
        }


    }

}