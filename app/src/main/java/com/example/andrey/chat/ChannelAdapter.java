package com.example.andrey.chat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    private ArrayList<Channel> list;
    Context context;

    public ChannelAdapter(ArrayList<Channel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        //get View
        View v = LayoutInflater.from(context)
                .inflate(R.layout.chat_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Channel channel = list.get(position);
        User user = channel.getUsers().get(0);
        //holder.avatar.setText();
        if (!channel.getLastMessage().getIsRead()){
            holder.unreadCount.setVisibility(View.VISIBLE);
            holder.unreadCount.setText(channel.getUnreadMessagesCount().toString());
        }

        holder.time.setText(getDate(channel.getLastMessage().getCreateDate()));
        holder.name.setText(channel.getUsers().get(0).getFirstName() +
                " " + channel.getUsers().get(0).getLastName());
        holder.message.setText(channel.getLastMessage().getText());
        Picasso.with(holder.avatar.getContext()).load(user.getPhoto()).into(holder.avatar);
        Log.e("time", getDate(channel.getLastMessage().getCreateDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView name, message, time, unreadCount;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            message = (TextView) itemView.findViewById(R.id.message);
            time = (TextView) itemView.findViewById(R.id.time);
            unreadCount = (TextView) itemView.findViewById(R.id.unread_count);
        }
    }

    public String getDate(String s){
        String dtStart = s.substring(0,16); //"2010-10-15T09:27";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        try {
            date = format.parse(dtStart);
            System.out.println(DateUtils.getRelativeTimeSpanString(date.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar today = Calendar.getInstance();
        Calendar myDate = Calendar.getInstance();
        myDate.setTime(date);

            //format if is today message
        if (DateUtils.isToday(date.getTime())) {  //today.getTimeInMillis()
            return timeOnlyFormat.format(date.getTime());
        } else
            //format in other way
        return String.valueOf(DateUtils.getRelativeTimeSpanString(context,date.getTime()) +
                ", " + timeOnlyFormat.format(date.getTime()));
        }

}


