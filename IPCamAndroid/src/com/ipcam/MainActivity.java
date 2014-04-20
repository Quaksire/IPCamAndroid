package com.ipcam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ipcam.websocket.WebSocketClient;
import java.net.URI;

public class MainActivity extends Activity {

    private EditText ipView;
    private Button checkView;
    private WebSocketClient client;
    
    private static final String TAG = "IPCam";
    
    private final String PROTOCOL = "ws://";
    private final String PORT = ":8080";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ServerService cast = new ServerService("asdf");
        

        ipView = (EditText) findViewById(R.id.ip);
        checkView = (Button) findViewById(R.id.check);
        
        checkView.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, ipView.getText().toString());
                connect(ipView.getText().toString());
            }
        });
    }
    
    private void connect(String ip){
        String uri = new StringBuilder()
                .append(PROTOCOL)
                .append(ip)
                .append(PORT)
                .toString();
        
        Intent msgIntent = new Intent(MainActivity.this, ServerService.class);
        msgIntent.putExtra(ServerService._URI, uri);
        startService(msgIntent);
    }
}
