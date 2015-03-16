package com.loredan13.chat;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.UUID;

/**
 * Created by loredan13 on 12.03.2015.
 */
public class Message implements Comparable<Message> {
    public UUID senderId;
    public String senderName;
    public String body;
    public long timestamp;
    public boolean isRead;

    public Message(UUID _senderId, String _senderName, String _body, long _timestamp) {
        senderId = _senderId;
        senderName = _senderName;
        body = _body;
        timestamp = _timestamp;
        isRead = true;
    }

    public Message(UUID _senderId, String _senderName, String _body, long _timestamp, boolean _isRead) {
        this(_senderId, _senderName, _body, _timestamp);
        isRead = _isRead;
    }

    public Message() {
        return;
    }

    public String toJsonString() {
        try {
            return new JSONStringer()
                    .object()
                    .key("senderId")
                    .value(senderId.toString())
                    .key("senderName")
                    .value(senderName)
                    .key("body")
                    .value(body)
                    .key("timestamp")
                    .value(timestamp)
                    .endObject()
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message parseFromJsonString(String jsonString) {
        Message message = new Message();

        try {
            JSONObject json = new JSONObject(jsonString);
            message.senderId = UUID.fromString(json.getString("senderId"));
            message.senderName = json.getString("senderName");
            message.body = json.getString("body");
            message.timestamp = json.getLong("timestamp");
            return message;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int compareTo(Message message) {
        return (int) (timestamp - message.timestamp);
    }
}
