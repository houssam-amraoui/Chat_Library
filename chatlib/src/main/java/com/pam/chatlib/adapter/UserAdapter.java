package com.pam.chatlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.helpers.Tools;
import com.pam.chatlib.interfaces.AdapterClickListener;
import com.pam.chatlib.models.ItemConversationIds;
import com.pam.chatlib.models.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users = new ArrayList<>();
    Context context;
    String currentUserToken;
    ItemConversationIds itemConversationIds;
    AdapterClickListener adapterClickListener;


    public UserAdapter(Context context, String currentUserToken, ItemConversationIds itemConversationIds, AdapterClickListener adapterClickListener) {
        this.context = context;
        this.currentUserToken = currentUserToken;
        this.itemConversationIds = itemConversationIds;
        this.adapterClickListener = adapterClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view;

        view = inflater.inflate(itemConversationIds.getLayoutConversationId(), parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        User user = users.get(position);

        viewHolder.favorite.setVisibility(View.GONE);

        Tools.getAvatars(user.getPic(), viewHolder.userPhoto);
        viewHolder.fullName.setText(user.getName());

        viewHolder.iconBadge.setVisibility(View.GONE);

        viewHolder.bind(position,user,adapterClickListener);


        //   viewHolder.description.setText(connection.getLastMessage());

        // TODO: 9/4/2022 connection type is message or visitor

         /*   if (connection.getConnectionType() != null && connection.getConnectionType().equals(ConnectionListModel.CONNECTION_TYPE_MESSAGE)){

               {


// TODO: 9/4/2022
                    switch (connection.getColMessageType()) {
                        case ConnectionListModel.MESSAGE_TYPE_CHAT:
                               viewHolder.description.setText(connection.getLastMessage());

                            if (!connection.isRead() && !connection.getUserFrom().equals(User.getUser())){
                                viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.black));

                            } else {
                                viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.gray_dark));
                            }

                            viewHolder.description.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                            break;

                        case ConnectionListModel.MESSAGE_TYPE_IMAGE:

                            if (connection.getUserFrom().equals(User.getUser())) {
                                viewHolder.description.setText(mActivity.getString(R.string.image_message));

                            } else {

                                viewHolder.description.setText(mActivity.getString(R.string.image_message_received));
                            }

                            if (!connection.isRead() && !connection.getUserFrom().equals(User.getUser())){
                                viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.black));

                            } else {
                                viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.gray_dark));
                            }

                            viewHolder.description.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                            break;

                        case ConnectionListModel.MESSAGE_TYPE_CALL:

                            viewHolder.description.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_generic_media_video, 0, 0, 0);
                            int padding = mActivity.getResources().getDimensionPixelSize(R.dimen.call_connection_padding);
                            viewHolder.description.setCompoundDrawablePadding(padding);

                            if (connection.getCall() != null && connection.getUserFrom().equals(User.getUser())) {

                                if (connection.getCall().isAccepted()) {
                                    viewHolder.description.setText(String.format(mActivity.getString(R.string.calls_caller_user), connection.getUserTo().getColFirstName(), connection.getCall().getDuration()));
                                    viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.gray_dark));
                                    QuickHelp.setDrawableTint(viewHolder.description, R.color.gray_dark);
                                } else {
                                    viewHolder.description.setText(String.format(mActivity.getString(R.string.calls_caller_missed_user), connection.getUserTo().getColFirstName()));
                                    viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.generic_red));
                                    QuickHelp.setDrawableTint(viewHolder.description, R.color.generic_red);
                                }

                            } else if (connection.getCall() != null && connection.getUserTo().equals(User.getUser())) {

                                if (connection.getCall() != null && connection.getCall().isAccepted()) {
                                    viewHolder.description.setText(String.format(mActivity.getString(R.string.calls_callee_user), connection.getUserFrom().getColFirstName(), connection.getCall().getDuration()));
                                    viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.gray_dark));
                                    QuickHelp.setDrawableTint(viewHolder.description, R.color.gray_dark);
                                } else {
                                    viewHolder.description.setText(String.format(mActivity.getString(R.string.calls_callee_missed_user), connection.getUserFrom().getColFirstName()));
                                    viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.generic_red));
                                    QuickHelp.setDrawableTint(viewHolder.description, R.color.generic_red);
                                }

                            } else {

                                viewHolder.description.setTextColor(mActivity.getResources().getColor(R.color.gray_dark));
                                viewHolder.description.setText(mActivity.getString(R.string.call_no_object));
                            }

                            break;
                    }

                    if (!connection.isRead() && !connection.getUserFrom().equals(User.getUser())){
                        viewHolder.countContainer.setVisibility(View.VISIBLE);
                        viewHolder.counter.setVisibility(View.VISIBLE);
                        viewHolder.counter.setText(String.valueOf(connection.getCount()));

                    } else {

                        viewHolder.countContainer.setVisibility(View.GONE);
                    }
                }


            } else if (connection.getConnectionType() != null && connection.getConnectionType().equals(ConnectionListModel.CONNECTION_TYPE_VISITOR)){

                viewHolder.favorite.setVisibility(View.GONE);

                viewHolder.countContainer.setVisibility(View.GONE);
                viewHolder.iconBadge.setVisibility(View.GONE);

                QuickHelp.getAvatars(connection.getUserFrom(), viewHolder.userPhoto);
                viewHolder.fullName.setText(connection.getUserFrom().getColFullName());
                viewHolder.description.setText(String.format("%s %s", mActivity.getString(R.string.visited_you), DateUtils.getTimeAgo(connection.getUpdatedAt().getTime())));

                viewHolder.description.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
            else if (connection.getConnectionType() != null && connection.getConnectionType().equals(ConnectionListModel.CONNECTION_TYPE_FAVORITE)){

                viewHolder.description.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                viewHolder.favorite.setVisibility(View.VISIBLE);

                viewHolder.countContainer.setVisibility(View.GONE);
                viewHolder.iconBadge.setVisibility(View.GONE);

                viewHolder.description.setText(String.format("%s %s", mActivity.getString(R.string.your_favorite), DateUtils.getTimeAgo(connection.getUpdatedAt().getTime())));

                if (connection.getUserFrom().equals(User.getUser())){

                    QuickHelp.getAvatars(connection.getUserTo(), viewHolder.userPhoto);
                    viewHolder.fullName.setText(connection.getUserTo().getColFullName());
                    viewHolder.favorite.setImageResource(R.drawable.ic_profile_favorite_added);

                } else {

                    QuickHelp.getAvatars(connection.getUserFrom()  , viewHolder.userPhoto);
                    viewHolder.fullName.setText(connection.getUserFrom().getColFullName());
                    viewHolder.favorite.setImageResource(R.drawable.ic_profile_favorite);
                }
            }
*/

/*
            viewHolder.userItemLayout.setOnClickListener(v -> {

                switch (connection.getConnectionType()) {
                    case ConnectionListModel.CONNECTION_TYPE_MESSAGE:

                        if (connection.getUserFrom().equals(User.getUser())) {
                            QuickHelp.goToActivityChat(mActivity, connection.getUserTo());
                        } else {
                            QuickHelp.goToActivityChat(mActivity, connection.getUserFrom());
                        }


                        break;
                    case ConnectionListModel.CONNECTION_TYPE_VISITOR:

                        QuickActions.showProfile(mActivity, connection.getUserFrom(), false);

                        break;
                    case ConnectionListModel.CONNECTION_TYPE_FAVORITE:

                        if (connection.getUserFrom().equals(User.getUser())) {

                            QuickActions.showProfile(mActivity, connection.getUserTo(), false);

                        } else {

                            QuickActions.showProfile(mActivity, connection.getUserFrom(), false);
                        }
                        break;
                }
            });*/



/*
            if (connection.getUserFrom().equals(User.getUser())){

                if (connection.getUserTo().getLastOnline() != null){


                    if (!User.getUser().getPrivacyOnlineStatusEnabled()){

                        viewHolder.userStatus.setVisibility(View.GONE);

                    } else if (!connection.getUserTo().getPrivacyOnlineStatusEnabled()){

                        viewHolder.userStatus.setVisibility(View.GONE);

                    } else {

                        if (System.currentTimeMillis() - connection.getUserTo().getUpdatedAt().getTime() > Constants.TIME_TO_OFFLINE) {

                            viewHolder.userStatus.setVisibility(View.GONE);

                        } else if (System.currentTimeMillis() - connection.getUserTo().getUpdatedAt().getTime() > Constants.TIME_TO_SOON) {

                            viewHolder.userStatus.setVisibility(View.VISIBLE);
                            viewHolder.userStatus.setImageResource(R.color.orange_500);

                        } else {

                            viewHolder.userStatus.setVisibility(View.VISIBLE);
                            viewHolder.userStatus.setImageResource(R.color.green_500);
                        }
                    }

                } else {

                    viewHolder.userStatus.setVisibility(View.GONE);
                }

            }
            else {

                if (connection.getUserFrom().getLastOnline() != null){


                    if (!User.getUser().getPrivacyOnlineStatusEnabled()){

                        viewHolder.userStatus.setVisibility(View.GONE);

                    } else if (!connection.getUserFrom().getPrivacyOnlineStatusEnabled()){

                        viewHolder.userStatus.setVisibility(View.GONE);

                    } else {

                        if (System.currentTimeMillis() - connection.getUserFrom().getUpdatedAt().getTime() > Constants.TIME_TO_OFFLINE) {

                            viewHolder.userStatus.setVisibility(View.GONE);

                        } else if (System.currentTimeMillis() - connection.getUserFrom().getUpdatedAt().getTime() > Constants.TIME_TO_SOON) {

                            viewHolder.userStatus.setVisibility(View.VISIBLE);
                            viewHolder.userStatus.setImageResource(R.color.orange_500);

                        } else {

                            viewHolder.userStatus.setVisibility(View.VISIBLE);
                            viewHolder.userStatus.setImageResource(R.color.green_500);
                        }
                    }

                } else {

                    viewHolder.userStatus.setVisibility(View.GONE);
                }
            }*/


    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addItem(User user) {
        this.users.add(user);
        notifyItemInserted(users.size() - 1);
    }


    public void addItems(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }
    public void removeItem(int pos) {
        users.remove(pos);
        notifyItemRemoved(pos);
    }
    public void clearItems() {
        this.users.clear();
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userItemLayout;
        ImageView userPhoto, userStatus;
        TextView fullName, description, counter;
        ImageView favorite, iconBadge;
        FrameLayout countContainer;

        ViewHolder(View v) {
            super(v);

            userItemLayout = v.findViewById(itemConversationIds.getUserItemLayoutId());
            userPhoto = v.findViewById(itemConversationIds.getUserPhotoId());
            fullName = v.findViewById(itemConversationIds.getFullNameId());
            countContainer = v.findViewById(itemConversationIds.getCountContainerId());
            counter = v.findViewById(itemConversationIds.getCounterId());
            iconBadge = v.findViewById(itemConversationIds.getIconBadgeId());
            description = v.findViewById(itemConversationIds.getDescriptionId());
            favorite = v.findViewById(itemConversationIds.getFavoriteId());
            userStatus = v.findViewById(itemConversationIds.getUserStatusId());
        }
        public void bind( int pos, User user, AdapterClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(pos,user,v);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return listener.onLongItemClick(pos,user,v);
                }

            });


        }
    }}

