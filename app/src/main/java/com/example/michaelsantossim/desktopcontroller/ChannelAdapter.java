package com.example.michaelsantossim.desktopcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Santos Sim on 26/11/2016.
 */

public class ChannelAdapter extends BaseAdapter {

    private List<String> channels;
    private static LayoutInflater inflater = null;
    Context context;

    List<Integer> images = new ArrayList<Integer>(){
        {
            add(R.drawable.bigbang);
            add(R.drawable.chaves);
            add(R.drawable.chapolin);
        }
    };

    ChannelAdapter(List<String> channels){
        context = MainActivity.instance;
        this.channels = channels;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int i) {
        return channels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return channels.size() > i ? -1 : i;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.channel_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.channel_name);
        holder.img=(ImageView) rowView.findViewById(R.id.channel_image);
        //holder.img.setImageResource();
        holder.tv.setText(channels.get(i));
        holder.img.setImageResource(images.get(i));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.sendPostRequest("chrome:" + holder.tv.getText().toString());
            }
        });
        return rowView;
    }
}
