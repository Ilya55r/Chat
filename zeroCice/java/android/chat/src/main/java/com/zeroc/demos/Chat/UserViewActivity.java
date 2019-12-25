//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

package com.zeroc.demos.Chat;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zeroc.demos.Chat.service.ChatRoomListener;

public class UserViewActivity extends SessionActivity
{
    private List<String> _users = new ArrayList<String>();
    private ArrayAdapter<String> _adapter;

    private ChatRoomListener _listener = new ChatRoomListener()
    {
        public void init(final List<String> users)
        {
            _users.clear();
            _users.addAll(users);
            _adapter.notifyDataSetChanged();
        }

        public void join(long timestamp, final String name)
        {
            _adapter.add(name);
        }

        public void leave(long timestamp, final String name)
        {
            _adapter.remove(name);
        }

        public void send(long timestamp, String name, String message)
        {
        }

        public void error()
        {
            showDialogFatal();
        }

        public void inactivity()
        {
            showDialogFatal();
        }
    };

    ChatRoomListener getChatRoomListener()
    {
        return _listener;
    }

    boolean replayEvents()
    {
        return false;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        ListView userList = (ListView)findViewById(R.id.list);
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _users);
        userList.setAdapter(_adapter);
    }
}
