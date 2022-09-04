package com.pam.chatlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.models.ItemMessageIds;
import com.pam.chatlib.models.Message;
import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter1 extends RecyclerView.Adapter<ChatAdapter1.BodyHolder> {
    private static final int CHAT_IN = 0;
    private static final int CHAT_OUT = 1;


    Context context;
    String currentUserToken;

    ItemMessageIds itemMessageIds;




    List<Message> messages = new ArrayList<>();

    public ChatAdapter1(Context context, String currentUserToken, ItemMessageIds itemMessageIds) {
        this.context = context;
        this.currentUserToken = currentUserToken;
        this.itemMessageIds = itemMessageIds;

    }

    public void addItem(Message message) {
        this.messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }
    public void addMoreItems(List<Message> messages) {
        this.messages.addAll(messages);
       // setModelSize(itemCount + messages.size());
        notifyDataSetChanged();
    }
    public void addNewItem(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    public void addItems(List<Message> messages) {
         this.messages.clear();
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }
    public void removeItem(int pos) {
        messages.remove(pos);
        notifyItemRemoved(pos);
    }
    public void clearItems() {
        this.messages.clear();
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {
        final boolean isMe = messages.get(position).getId_user().equals(currentUserToken);
        if(isMe){
            return CHAT_OUT;
        } else {
            return CHAT_IN;
        }
    }

    @NonNull
    @Override
    public BodyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CHAT_IN:
                return new BodyHolder(LayoutInflater.from(context).inflate(itemMessageIds.getLayoutReceiverId(),parent,false));

            case CHAT_OUT:

                return  new BodyHolder(LayoutInflater.from(context).inflate(itemMessageIds.getLayoutSenderId(),parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter1.BodyHolder holder, int position) {
        Message message = messages.get(position);
        int viewType=getItemViewType(position);
        switch (viewType){
            case CHAT_IN:
                holder.messageText.setText(message.getMessage());
                break;
            case CHAT_OUT:
                holder.messageText.setText(message.getMessage());

        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class BodyHolder extends RecyclerView.ViewHolder {
        EmojiTextView messageText;
        TextView timeText;
        TextView status;
        ProgressBar mProgressBar;
        RelativeLayout mImageLayout;
        ImageView mImageMsg;

        public BodyHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(itemMessageIds.getMessageTextId());
            timeText = itemView.findViewById(itemMessageIds.getTimeTextId());
            status = itemView.findViewById(itemMessageIds.getStatusId());
            mProgressBar = itemView.findViewById(itemMessageIds.getmProgressBarId());
            mImageLayout = itemView.findViewById(itemMessageIds.getmImageLayoutId());
            mImageMsg = itemView.findViewById(itemMessageIds.getmImageMsgId());
        }
    }
}
