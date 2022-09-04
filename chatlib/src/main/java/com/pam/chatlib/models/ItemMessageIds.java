package com.pam.chatlib.models;

public class ItemMessageIds {

    int layoutReceiverId;
    int layoutSenderId;
    int messageTextId;
    int timeTextId;
    int statusId;
    int mProgressBarId;
    int mImageLayoutId;
    int mImageMsgId;

    public ItemMessageIds(int layoutReceiverId, int layoutSenderId, int messageTextId, int timeTextId, int statusId, int mProgressBarId, int mImageLayoutId, int mImageMsgId) {
        this.layoutReceiverId = layoutReceiverId;
        this.layoutSenderId = layoutSenderId;
        this.messageTextId = messageTextId;
        this.timeTextId = timeTextId;
        this.statusId = statusId;
        this.mProgressBarId = mProgressBarId;
        this.mImageLayoutId = mImageLayoutId;
        this.mImageMsgId = mImageMsgId;
    }

    public int getLayoutReceiverId() {
        return layoutReceiverId;
    }

    public int getLayoutSenderId() {
        return layoutSenderId;
    }

    public int getMessageTextId() {
        return messageTextId;
    }

    public int getTimeTextId() {
        return timeTextId;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getmProgressBarId() {
        return mProgressBarId;
    }

    public int getmImageLayoutId() {
        return mImageLayoutId;
    }

    public int getmImageMsgId() {
        return mImageMsgId;
    }
}
