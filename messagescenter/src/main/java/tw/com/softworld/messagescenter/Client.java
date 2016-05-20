package tw.com.softworld.messagescenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Catherine on 16/5/5.
 */
public class Client {
    private static final String TAG = Client.class.getSimpleName();
    private Context ctx;
    private Result result;
    private CustomReceiver cr;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;


    /**
     * Before you use gotMessages(), you should initialize this constructor
     *
     * @param ctx
     * @param cr
     */
    public Client(Context ctx, CustomReceiver cr) {
        this.ctx = ctx;
        this.cr = cr;
        this.result = new Result();
    }

    public void gotMessages(final String action) {
        broadcastManager = LocalBroadcastManager.getInstance(ctx);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                CLog.d(TAG, "You got a message");
                if (Config.messagesList.get(action).toString().equals("MESSAGES_BUNDLE")) {
                    result.setBundle(intent.getBundleExtra("MESSAGES_BUNDLE"));
                    cr.onBroadcastReceive(result);
                } else if (Config.messagesList.get(action).toString().equals("MESSAGES_BOOLEAN")) {
                    result.setBoolean(intent.getBooleanExtra("MESSAGES_BOOLEAN", false));
                    cr.onBroadcastReceive(result);
                } else if (Config.messagesList.get(action).toString().equals("MESSAGES_STRING")) {
                    result.setString(intent.getStringExtra("MESSAGES_STRING"));
                    cr.onBroadcastReceive(result);
                } else if (Config.messagesList.get(action).toString().equals("MESSAGES_INT")) {
                    result.setInt(intent.getIntExtra("MESSAGES_INT", -1));
                    cr.onBroadcastReceive(result);
                } else if (Config.messagesList.get(action).toString().equals("MESSAGES_DOUBLE")) {
                    result.setDouble(intent.getDoubleExtra("MESSAGES_DOUBLE", 0));
                    cr.onBroadcastReceive(result);
                }
                Config.messagesList.remove(action);
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * You should unregister receiver when destroy app
     */
    public void release() {
        if (broadcastReceiver != null)
            broadcastManager.unregisterReceiver(broadcastReceiver);
    }
}