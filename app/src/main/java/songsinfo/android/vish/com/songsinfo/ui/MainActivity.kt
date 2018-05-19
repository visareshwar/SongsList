package songsinfo.android.vish.com.songsinfo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import songsinfo.android.vish.com.songsinfo.R
import songsinfo.android.vish.com.songsinfo.db.entity.Song
import songsinfo.android.vish.com.songsinfo.utilities.InjectionUtil
import songsinfo.android.vish.com.songsinfo.viewmodel.SongsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var songsViewModel: SongsViewModel
    private val mSongsList: ArrayList<Song> = ArrayList<Song>()

    private lateinit var songsAdaptor: SongsAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        songsAdaptor = SongsAdaptor(mSongsList)
        val layoutManager = LinearLayoutManager(this)
        music_list_recycler_view.adapter = songsAdaptor
        music_list_recycler_view.layoutManager = layoutManager

        val factory = InjectionUtil.provideSongsViewModelFactory(this)
        songsViewModel = ViewModelProviders.of(this, factory).get(SongsViewModel::class.java)
        getDataFromDb()
    }


    private fun getDataFromDb(queryStr: String = "") {
        songsViewModel.getSongList(queryStr).observe(this, Observer { songsList ->
            if (songsList != null) {
                populateList(songsList)
            }
        })
    }

    private fun populateList(songsList: List<Song>) {
        mSongsList.clear()
        mSongsList.addAll(songsList)
        songsAdaptor.notifyDataSetChanged()
    }

    fun onSearchClicked(view: View) {
        getDataFromDb(search_edit_text.text.toString())
    }
}
