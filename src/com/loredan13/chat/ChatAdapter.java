package com.loredan13.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by loredan13 on 12.03.2015.
 */
public class ChatAdapter extends ArrayAdapter<Message> {
    public ChatAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);

        convertView.setBackgroundColor(getContext().getResources().getColor(message.senderName.equals(R.string.me) ?
                R.color.message_background_me : R.color.message_background_other));

        ((TextView) convertView.findViewById(R.id.message_sender)).setText(message.senderName + ":");
        ((TextView) convertView.findViewById(R.id.message_timestamp)).setText("00:00");
        ((TextView) convertView.findViewById(R.id.message_body)).setText(message.body);
        return convertView;
    }
}
