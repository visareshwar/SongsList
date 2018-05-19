package songsinfo.android.vish.com.songsinfo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import songsinfo.android.vish.com.songsinfo.db.dao.SongDao;
import songsinfo.android.vish.com.songsinfo.db.entity.Song;
import songsinfo.android.vish.com.songsinfo.utilities.Constants;

@Database(entities = {Song.class}, version = 1, exportSchema = false)
public abstract class SongsDatabase extends RoomDatabase {
    private static final String TAG = SongsDatabase.class.getSimpleName();


    // For Singleton instantiation
    private static final Object LOCK = new Object();

    private static SongsDatabase sInstance;

    public static SongsDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        SongsDatabase.class, Constants.DATABASE_NAME).build();
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract SongDao getLocationDao();
}
