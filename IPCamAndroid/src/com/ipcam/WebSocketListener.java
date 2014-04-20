package com.ipcam;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.ipcam.websocket.WebSocketClient;

/**
 *
 * @author Quaksire
 */
public class WebSocketListener implements WebSocketClient.Listener {

    private final String TAG = "IPCam";
    
    private Context context;
    
    public WebSocketListener(Context context){
        this.context = context;
    }

    public void onConnect() {
        Log.i(TAG, "onConnect");
        sendNotification(TAG, "Connected");
    }

    public void onMessage(String message) {
        Log.i(TAG, "onMessage");
        sendNotification(TAG, "onMessage " + message);
    }

    public void onMessage(byte[] data) {
        Log.i(TAG, "onMessage");
        sendNotification(TAG, "onMessage" + new String(data));
    }

    public void onDisconnect(int code, String reason) {
        Log.i(TAG, "onDisconnect");
        sendNotification(TAG, "onDisconnect");
    }

    public void onError(Exception error) {
        Log.i(TAG, "onError");
        sendNotification(TAG, "onError " + error.getMessage());
    }

    private void sendNotification(String title, String text) {
        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true).build();

        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
