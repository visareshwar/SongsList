package songsinfo.android.vish.com.songsinfo.utilities

import android.content.Context
import songsinfo.android.vish.com.songsinfo.AppExecutor
import songsinfo.android.vish.com.songsinfo.SongsApp
import songsinfo.android.vish.com.songsinfo.db.SongsDatabase
import songsinfo.android.vish.com.songsinfo.repo.SongNetworkRepo
import songsinfo.android.vish.com.songsinfo.repo.SongsDataRepo
import songsinfo.android.vish.com.songsinfo.viewmodel.SongsViewModelFactory

object InjectionUtil {

    private fun provideSongsNetworkRepo(context: Context): SongNetworkRepo {
        val songNetworkRepo: SongNetworkRepo = SongNetworkRepo.getInstance(context)
        return songNetworkRepo
    }

    private fun provideSongsRepo(context: Context): SongsDataRepo {
        val songNetworkRepo = provideSongsNetworkRepo(context)
        val appExecutor = AppExecutor.instance
        val songsDatabase = SongsDatabase.getsInstance(context)
        return SongsDataRepo.getInstance(songsDatabase.locationDao, songNetworkRepo, appExecutor)

    }


    fun provideSongsViewModelFactory(context: Context): SongsViewModelFactory {
        return SongsViewModelFactory(provideSongsRepo(context))
    }

    fun isFetchNeeded(): Boolean {
        val sharedPref = SongsApp.getAppInstace().getSharedPreferences(Constants.PREF_APP_CONFIG, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(Constants.KEY_FETCH_NEEDED, true)
    }

    fun setFetchNeeded(needed: Boolean = false) {
        val sharedPref = SongsApp.getAppInstace().getSharedPreferences(Constants.PREF_APP_CONFIG, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(Constants.KEY_FETCH_NEEDED, needed)
        editor.apply()
    }
}