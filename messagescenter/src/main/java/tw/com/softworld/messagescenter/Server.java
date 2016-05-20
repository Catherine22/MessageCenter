package tw.com.softworld.messagescenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Catherine on 16/5/19.
 */
public class Server {
    private static final String TAG = Server.class.getSimpleName();

    private Context ctx;
    private AsyncResponse ar;

    /**
     * Before you use pushTypes(), you should initialize this constructor
     *
     * @param ctx
     */
    public Server(Context ctx, AsyncResponse ar) {
        this.ctx = ctx;
        this.ar = ar;
    }

    /**
     * Send a message with broadcast
     *
     * @param action
     * @param messages
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
}
