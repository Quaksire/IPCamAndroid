package com.ipcam;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText ipView;
    private Button checkView, checkHistory;

    private final String PROTOCOL = "ws://";
    private final String HTTP_PROTOCOL = "http://";
    private final String PORT = ":8080";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ipView = (EditText) findViewById(R.id.ip);
        checkView = (Button) findViewById(R.id.check);
        checkHistory = (Button) findViewById(R.id.web);

        checkView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                connect(ipView.getText().toString());
            }
        });

        checkHistory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openWeb(ipView.getText().toString());
            }
        });

    }

    private void openWeb(String ip) {
        String uri = new StringBuilder()
                .append(HTTP_PROTOCOL)
                .append(ip)
                .append("/timeline.html")
                .toString();
        
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(browserIntent);
    }

    private void connect(String ip) {
        String uri = new StringBuilder()
                .append(HTTP_PROTOCOL)
                .append(ip)
                .append(PORT)
                .toString();

        Intent msgIntent = new Intent(MainActivity.this, ServerService.class);
        msgIntent.putExtra(ServerService._URI, uri);
        startService(msgIntent);
    }
}
