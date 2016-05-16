package tw.com.softworld.messagescenter;

import android.util.Log;

/**
 * Created by Catherine on 16/5/9.
 */
public class CLog {

    public static void v(String tab, String message) {
        if (Config.showDebugLog) {
            Log.v(tab, message);
        }
    }

    public static void d(String tab, String message) {
        if (Config.showDebugLog) {
            Log.d(tab, message);
        }
    }

    public static void e(String tab, String message) {
        if (Config.showDebugLog) {
            Log.e(tab, message);
        }
    }

    public static void i(String tab, String message) {
        if (Config.showDebugLog) {
            Log.i(tab, message);
        }
    }

    public static void w(String tab, String message) {
        if (Config.showDebugLog) {
            Log.w(tab, message);
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
