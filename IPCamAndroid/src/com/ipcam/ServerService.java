package com.ipcam;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.ipcam.websocket.WebSocketClient;
import java.net.URI;

/**
 *
 * @author Quaksire
 */
public class ServerService extends IntentService {

    public static final String _URI = "uri";

    public ServerService(String name) {
        super(name);
    }
    
    public ServerService(){
        super(_URI);
    }    

    @Override
    protected void onHandleIntent(Intent intent) {
        
        String uri = intent.getStringExtra(_URI);

        Log.i("IPCam", "onHandleIntent " + uri);
        
        WebSocketClient client = new WebSocketClient(
                URI.create(uri),
                new WebSocketListener(getApplicationContext()),
                null);
        
        client.connect();
    }

}
