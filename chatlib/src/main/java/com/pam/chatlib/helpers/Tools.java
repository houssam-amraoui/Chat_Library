package com.pam.chatlib.helpers;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.pam.chatlib.R;

public class Tools {
    public static void getAvatars(String image, ImageView imageView){

     /*   ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(, R.color.highlight_light_ripple));

        // Load profile Photos
        if (user.getProfilePhotos().size() > 0) {

            Glide.with(Application.getInstance().getApplicationContext())
                    .load(user.getProfilePhotos().get(user.getAvatarPhotoPosition()).getUrl())
                    .error(colorDrawable)
                    .centerCrop()
                    .circleCrop()
                    .fitCenter()
                    .placeholder(colorDrawable)
                    .into(imageView);

        } else {

            Glide.with(Application.getInstance().getApplicationContext())
                    .load(colorDrawable)
                    .into(imageView);

        }*/
    }
}
