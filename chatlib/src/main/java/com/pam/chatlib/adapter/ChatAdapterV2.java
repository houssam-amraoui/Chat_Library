package com.pam.chatlib.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.emoji.widget.EmojiTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.Builder;
import com.pam.chatlib.R;
import com.pam.chatlib.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapterV2 extends RecyclerView.Adapter<ChatAdapterV2.BodyHolder> {
    private static final int CHAT_IN = 0;
    private static final int CHAT_OUT = 1;

    int sendId, receivedId;
    Context context;
    String currentUser;
    int mesageText;
    List<MessageModel> messageModelList = new ArrayList<>();

    public ChatAdapterV2(Context c,String currentUser,int sendId) {
        this.context = c;
        this.currentUser=currentUser;
        this.sendId=sendId;
        this.receivedId=receivedId;
    }
    @Override
    public int getItemViewType(int position) {
        final boolean isMe = messageModelList.get(position).getSender_id().equals(currentUser);
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
                return new BodyHolder(LayoutInflater.from(context).inflate(receivedId,parent,false));
            case CHAT_OUT:
                return  new BodyHolder(LayoutInflater.from(context).inflate(sendId,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapterV2.BodyHolder holder, int position) {
        MessageModel messageModel=messageModelList.get(position);
        int viewType=getItemViewType(position);
        switch (viewType){
            case CHAT_IN:
                holder.messageText.setText(messageModel.getText());
                break;
            case CHAT_OUT:
                holder.messageText.setText(messageModel.getText());
        }
    }


    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class BodyHolder extends RecyclerView.ViewHolder {
        EmojiTextView messageText;
        /*TextView timeText;
        TextView status;
        ProgressBar mProgressBar;
        RelativeLayout mImageLayout;*/
        //RoundedImage mImageMsg;
        public BodyHolder(@NonNull View itemView) {
            super(itemView);
            messageText=itemView.findViewById(mesageText);
        }
    }
}
