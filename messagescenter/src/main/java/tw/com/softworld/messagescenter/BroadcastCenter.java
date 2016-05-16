package tw.com.softworld.messagescenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Catherine on 16/5/5.
 */
public class BroadcastCenter {
    private static final String TAG = BroadcastCenter.class.getSimpleName();
    private Context ctx;
    private Result result;
    private AsyncResponse ar;
    private CustomReceiver cr;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;

    /**
     * Before you use pushTypes(), you should initialize this constructor
     *
     * @param ctx
     */
    public BroadcastCenter(Context ctx) {
        this.ctx = ctx;
        this.ar = (AsyncResponse) ctx;
    }

    /**
     * Before you use gotMessages(), you should initialize this constructor
     *
     * @param ctx
     * @param cr
     */
    public BroadcastCenter(Context ctx, CustomReceiver cr) {
        this.ctx = ctx;
        this.cr = cr;
        this.result = new Result();
    }

    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushBundle(String action, Bundle messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_BUNDLE")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null || messages == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_BUNDLE", messages);
            Config.messagesList.put(action, "MESSAGES_BUNDLE");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
    }


    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushBoolean(String action, boolean messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_BOOLEAN")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_BOOLEAN", messages);
            Config.messagesList.put(action, "MESSAGES_BOOLEAN");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
    }


    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushString(String action, String messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_STRING")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null || messages == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_STRING", messages);
            Config.messagesList.put(action, "MESSAGES_STRING");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
    }


    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushInt(String action, int messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_INT")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_INT", messages);
            Config.messagesList.put(action, "MESSAGES_INT");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
    }


    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushDouble(String action, double messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_DOUBLE")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_DOUBLE", messages);
            Config.messagesList.put(action, "MESSAGES_DOUBLE");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
    }

    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
     * @throws NullPointerException
     */
    public void pushByte(String action, byte messages) {
        if (Config.messagesList.containsKey(action) && !Config.messagesList.get(action).toString().equals("MESSAGES_BYTE")) {
            ar.onFailure(ErrorMessages.MULTIPLE_VALUE);
            CLog.e(TAG, "MULTIPLE_VALUE:" + action);
        } else if (action == null)
            ar.onFailure(ErrorMessages.NULL_POINTER);
        else {
            Intent broadcast = new Intent(action);
            broadcast.putExtra("MESSAGES_BYTE", messages);
            Config.messagesList.put(action, "MESSAGES_BYTE");
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(broadcast);
        }
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