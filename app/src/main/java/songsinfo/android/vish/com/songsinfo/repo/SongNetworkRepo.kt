package songsinfo.android.vish.com.songsinfo.repo

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import songsinfo.android.vish.com.songsinfo.db.entity.Song
import songsinfo.android.vish.com.songsinfo.utilities.Constants


class SongNetworkRepo private constructor() {

    private lateinit var requestQueue: RequestQueue

    fun fetchDataFromServer() {

        val request = JsonArrayRequest(Constants.URL, Response.Listener { jsonArray ->
            val gson = Gson()
            Log.d("SongNetworkRepo", "jsonArray = $jsonArray")
            val listType = object : TypeToken<List<Song>>() {}.type
            val myModelList: List<Song> = gson.fromJson(jsonArray.toString(), listType)
            currentSongsData.postValue(myModelList)
        }, Response.ErrorListener { error ->
            error.printStackTrace()
        })

        requestQueue.add(request)
    }

    val currentSongsData: MutableLiveData<List<Song>> = MutableLiveData()


    companion object {

        @Volatile
        private var INSTANCE: SongNetworkRepo? = null

        fun getInstance(context: Context): SongNetworkRepo =
                SongNetworkRepo.INSTANCE ?: synchronized(this) {
                    SongNetworkRepo.INSTANCE
                            ?: buildSongNetworkRepo(context).also { SongNetworkRepo.INSTANCE = it }
                }

        private fun buildSongNetworkRepo(context: Context): SongNetworkRepo {
            val instance = SongNetworkRepo()
            instance.requestQueue = Volley.newRequestQueue(context.applicationContext)
            return instance
        }
    }
}