package com.example.michaelsantossim.desktopcontroller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChannelListActivity extends AppCompatActivity {
    public static List<String> channels = new ArrayList<String>(){
        {
            add("bigbang");
            add("chaves");
            add("chapolin");
        }
    };

    ListView lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        context = this;
        lv = (ListView) findViewById(R.id.channel_list);
        lv.setAdapter(new ChannelAdapter(channels));
    }


}
