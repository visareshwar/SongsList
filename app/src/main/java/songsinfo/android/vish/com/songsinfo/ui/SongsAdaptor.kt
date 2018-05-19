package songsinfo.android.vish.com.songsinfo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import songsinfo.android.vish.com.songsinfo.R
import songsinfo.android.vish.com.songsinfo.db.entity.Song

class SongsAdaptor(private val songsList: List<Song>) : RecyclerView.Adapter<SongsAdaptor.SongsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_music_list, parent, false)
        return SongsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.albumName.text = songsList[position].album
        holder.songNameTv.text = songsList[position].name
        holder.artistName.text = songsList[position].artist
    }


    inner class SongsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songNameTv = view.findViewById<TextView>(R.id.song_name_tv)!!
        val artistName = view.findViewById<TextView>(R.id.artist_name_tv)!!
        val albumName = view.findViewById<TextView>(R.id.album_name_tv)!!
    }

}