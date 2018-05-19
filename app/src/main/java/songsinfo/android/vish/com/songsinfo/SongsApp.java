package songsinfo.android.vish.com.songsinfo;

import android.app.Application;

public class SongsApp extends Application {
    private static SongsApp instance = null;

    public static SongsApp getAppInstace() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
