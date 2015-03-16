package com.loredan13.chat;

import android.content.Context;
import android.os.ParcelUuid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by loredan13 on 14.03.2015.
 */
public class Room {
    private LinkedList<Message> messages;

    public Room(Context context, ParcelUuid roomId) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(roomId.toString())));
            while (true) {
                String message = reader.readLine();
                if (message == null) {
                    break;
                }

                messages.add(Message.parseFromJsonString(message));
            }
            reader.close();
            Collections.sort(messages);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
