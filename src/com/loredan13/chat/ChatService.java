package com.loredan13.chat;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by loredan13 on 12.03.2015.
 */
public class ChatService extends Service {
    public static final int MSG_NEW_MESSAGE = 0;
    public static final int MSG_GET_MESSAGES = 1;

    public static final int NOTIFICATION_ID = 12315;

    private LinkedHashMap<UUID, Messenger> messengerMap;

    private class MessageHandler extends Handler {
        public UUID roomId;
        private LinkedList<com.loredan13.chat.Message> messages;

        public MessageHandler(ParcelUuid _roomId) {
            roomId = _roomId.getUuid();
            messages = new LinkedList<com.loredan13.chat.Message>();

            try {
                FileInputStream inputStream = openFileInput(roomId.toString());
                BufferedInputStream stream = new BufferedInputStream(inputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_NEW_MESSAGE:

                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        messengerMap = new LinkedHashMap<UUID, Messenger>();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        ParcelUuid roomId = intent.getParcelableExtra("roomId");
        Messenger messenger = new Messenger(new MessageHandler(roomId));
        messengerMap.put(roomId.getUuid(), messenger);
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        messengerMap.remove(((ParcelUuid) intent.getParcelableExtra("roomId")).getUuid());
        return super.onUnbind(intent);
    }
}
