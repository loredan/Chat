package com.loredan13.chat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ChatActivity extends Activity {
    private ChatAdapter chatAdapter;
    private ParcelUuid roomId;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //TODO: Initial actions
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chatAdapter = new ChatAdapter(this, R.layout.message_item);
        ((ListView) findViewById(R.id.chat_history)).setAdapter(chatAdapter);

        ((Button) findViewById(R.id.send_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Send action
            }
        });

        Intent startIntent = getIntent();
        roomId = startIntent.getParcelableExtra("room_id");

        bindService(new Intent(this, ChatService.class), connection, BIND_AUTO_CREATE);
    }
}
