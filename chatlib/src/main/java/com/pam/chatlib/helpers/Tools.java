package com.pam.chatlib.helpers;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.pam.chatlib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public static String getTimeStringFormat(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);
        return simpleDateFormat.format(c);
    }
}
