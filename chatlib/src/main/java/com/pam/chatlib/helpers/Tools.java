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
import java.util.Random;

public class Tools {

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
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

    public static String getRandomString(final int sizeOfRandomString) {

        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
