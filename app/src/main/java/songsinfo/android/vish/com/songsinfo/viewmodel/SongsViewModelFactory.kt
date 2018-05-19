package songsinfo.android.vish.com.songsinfo.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import songsinfo.android.vish.com.songsinfo.repo.SongsDataRepo

class SongsViewModelFactory(val songs: SongsDataRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongsViewModel(songs) as T
    }
}