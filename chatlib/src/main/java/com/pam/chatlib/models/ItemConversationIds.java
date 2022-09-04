package com.pam.chatlib.models;

public class ItemConversationIds {

    int layoutConversationId;

    int  userItemLayoutId;
    int  userPhotoId;
    int  fullNameId;
    int  countContainerId;
    int  counterId;
    int  iconBadgeId;
    int  descriptionId;
    int  favoriteId;
    int  userStatusId;

    public ItemConversationIds(int layoutConversationId, int userItemLayoutId, int userPhotoId, int fullNameId, int countContainerId, int counterId, int iconBadgeId, int descriptionId, int favoriteId, int userStatusId) {
        this.layoutConversationId = layoutConversationId;
        this.userItemLayoutId = userItemLayoutId;
        this.userPhotoId = userPhotoId;
        this.fullNameId = fullNameId;
        this.countContainerId = countContainerId;
        this.counterId = counterId;
        this.iconBadgeId = iconBadgeId;
        this.descriptionId = descriptionId;
        this.favoriteId = favoriteId;
        this.userStatusId = userStatusId;
    }

    public int getLayoutConversationId() {
        return layoutConversationId;
    }

    public int getUserItemLayoutId() {
        return userItemLayoutId;
    }

    public int getUserPhotoId() {
        return userPhotoId;
    }

    public int getFullNameId() {
        return fullNameId;
    }

    public int getCountContainerId() {
        return countContainerId;
    }

    public int getCounterId() {
        return counterId;
    }

    public int getIconBadgeId() {
        return iconBadgeId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public int getUserStatusId() {
        return userStatusId;
    }
}
