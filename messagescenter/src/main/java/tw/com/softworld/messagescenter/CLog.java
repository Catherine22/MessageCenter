package tw.com.softworld.messagescenter;

import android.util.Log;

/**
 * Created by Catherine on 16/5/9.
 * Soft-World Inc.
 * catherine919@soft-world.com.tw
 */
@SuppressWarnings("unused")
public class CLog {

    public static void v(String TAG, String message) {
        if (Config.showDebugLog) {
            Log.v(TAG, message);
        }
    }

    public static void d(String TAG, String message) {
        if (Config.showDebugLog) {
            Log.d(TAG, message);
        }
    }

    public static void e(String TAG, String message) {
        if (Config.showDebugLog) {
            Log.e(TAG, message);
        }
    }

    public static void i(String TAG, String message) {
        if (Config.showDebugLog) {
            Log.i(TAG, message);
        }
    }

    public static void w(String TAG, String message) {
        if (Config.showDebugLog) {
            Log.w(TAG, message);
        }
    }

    public static class out {
        public static void println(String message) {
            if (Config.showDebugLog) {
                System.out.println(message);
            }
        }
    }
}
