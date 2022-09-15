package com.pam.chat_lib;

import static com.pam.chat_lib.camera.ImagePro.CAMERA_CODE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.greysonparrelli.permiso.Permiso;
import com.pam.chat_lib.adapters.PhotosChatUploadAdapter;
import com.pam.chat_lib.camera.ImagePro;
import com.pam.chat_lib.custom.ItemOffsetDecoration;
import com.pam.chat_lib.models.UploadModel;
import com.pam.chatlib.ChatManager;
import com.pam.chatlib.models.Connection;
import com.pam.chatlib.models.User;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.EmojiPopup;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1003;

    RecyclerView chatRecycler;
    ImageButton sendBtn;
    //int user;
    ChatManager chatManager;
    EmojiEditText message;

    Serializable item;

////////// emoji /////
    LinearLayout rootView;
    ImageView mPickFile;
    EmojiPopup emojiPopup;
    ImageView mEmojiBtn;

    //////////// Gallery /////////////////////

    RelativeLayout mGalleryLayout;

    private ArrayList<UploadModel> mPhotosUploads;
    private PhotosChatUploadAdapter mPhotosUploadAdapter;

    private LinearLayout mNoAccountLayout;
    private TextView mNoPhotosText;
    private AppCompatButton mConenctButton;
    private ProgressBar mProgressbar;
    private RecyclerView mRecyclerViewGallery;

    private ArrayList<UploadModel> igModelArrayList;

    private ImagePro imagePro;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatRecycler = findViewById(R.id.listMessages);
        sendBtn = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.MessageWrapper);

        rootView = findViewById(R.id.activity_messages);
        mPickFile = findViewById(R.id.sendFileButton);
        mGalleryLayout = findViewById(R.id.gallery);
        mEmojiBtn = findViewById(R.id.emoji_btn);

        Bundle bundle =  getIntent().getExtras();

        chatManager = ((MyApp) this.getApplication()).getChatManager();
        chatManager.setChatRecycler(chatRecycler);

        setUpEmojiPopup();

        item = bundle.getSerializable("item");

        if (item instanceof Connection) {
            Connection connection = (Connection) item;
            chatManager.fitchMessages(connection.getRoom_id());
        }

        sendBtn.setOnClickListener(v -> {
            if (item instanceof User){
                item = chatManager.getConnectionFromUser( (User) item);
            }

            chatManager.sendMessage(message.getText().toString());

            message.getText().clear();


        });

        //////////////// Gallery //////////////

        mGalleryLayout = findViewById(R.id.gallery);
        mNoAccountLayout = findViewById(R.id.layout_no_account);
        mNoPhotosText = findViewById(R.id.no_image_text);
        mConenctButton = findViewById(R.id.btn_connect_instagram);
        mProgressbar = findViewById(R.id.progress_instagram);
        mRecyclerViewGallery = findViewById(R.id.recyclerList);

        //////////////// Gallery //////////////

        mPickFile.setOnClickListener(v -> {

            if (mGalleryLayout.getVisibility() == View.VISIBLE){

                mGalleryLayout.setVisibility(View.GONE);
                mPickFile.setImageResource(R.drawable.ic_chat_control_action_multimedia);
                //showKeyboard(v);
                //mEditText.hasFocus();

            } else {

                mGalleryLayout.setVisibility(View.VISIBLE);
                mPickFile.setImageResource(R.drawable.ic_chat_control_action_keyboard);

                hideKeyboard(v);

                if (emojiPopup.isShowing()) {
                    emojiPopup.dismiss();
                }

                initGallery();
            }
        });

        message.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus){

                mGalleryLayout.setVisibility(View.GONE);
                mPickFile.setImageResource(R.drawable.ic_chat_control_action_multimedia);
            }
        });




    }


    private void setUpEmojiPopup() {
        mEmojiBtn.setColorFilter(ContextCompat.getColor(ChatActivity.this, R.color.emoji_icons), PorterDuff.Mode.SRC_IN);

        emojiPopup = EmojiPopup.Builder.fromRootView(rootView)
                .setOnEmojiBackspaceClickListener(v -> { })
                .setOnEmojiClickListener((emoji, imageView) -> {

                    mGalleryLayout.setVisibility(View.GONE);
                    mPickFile.setImageResource(R.drawable.ic_chat_control_action_multimedia);
                })
                .setOnEmojiPopupShownListener(() -> mEmojiBtn.setImageResource(R.drawable.ic_chat_control_action_keyboard_small))
                .setOnSoftKeyboardOpenListener(keyBoardHeight -> { })
                .setOnEmojiPopupDismissListener(() -> mEmojiBtn.setImageResource(R.drawable.ic_chat_control_action_sticker_small))
                .setOnSoftKeyboardCloseListener(() -> { })
                .build(message);

        message.setOnClickListener(view -> {

            if (emojiPopup.isShowing()) {
                emojiPopup.dismiss();

                mGalleryLayout.setVisibility(View.GONE);
                mPickFile.setImageResource(R.drawable.ic_chat_control_action_multimedia);
            }
        });

        mEmojiBtn.setOnClickListener(view -> {

            mGalleryLayout.setVisibility(View.GONE);
            mPickFile.setImageResource(R.drawable.ic_chat_control_action_multimedia);

            emojiPopup.toggle();
        });
    }


    public void sendMessageFile(String imagePath){
        Toast.makeText(ChatActivity.this, "send image" + imagePath, Toast.LENGTH_SHORT).show();
    }

    public void initGallery() {

        mPhotosUploads = new ArrayList<>();
        mPhotosUploadAdapter = new PhotosChatUploadAdapter( this, mPhotosUploads);

        Permiso.getInstance().setActivity(this);

        isLoading(true, false);
        if(Build.VERSION.SDK_INT >= 21) {

            checkPermissions();

        } else {
            getMyGalleryPhotos();
        }

        mConenctButton.setOnClickListener(v1 -> checkPermissions());

        mRecyclerViewGallery.setAdapter(mPhotosUploadAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        mRecyclerViewGallery.addItemDecoration(itemDecoration);

        mRecyclerViewGallery.setBackgroundResource(R.color.white);
        mRecyclerViewGallery.setBackgroundColor(Color.WHITE);
        mRecyclerViewGallery.setLayoutManager(layoutManager);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permiso.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);

    }

    private void isLoading(boolean isLoading, boolean isLoaded) {

        if (isLoading){

            mProgressbar.setVisibility(View.VISIBLE);
            mNoAccountLayout.setVisibility(View.GONE);
            mRecyclerViewGallery.setVisibility(View.GONE);

        } else {

            if (isLoaded){

                mProgressbar.setVisibility(View.GONE);
                mNoAccountLayout.setVisibility(View.GONE);
                mRecyclerViewGallery.setVisibility(View.VISIBLE);

            } else {

                mProgressbar.setVisibility(View.GONE);
                mNoAccountLayout.setVisibility(View.VISIBLE);
                mRecyclerViewGallery.setVisibility(View.GONE);
            }

        }
    }

    private void checkPermissions() {

        Permiso.getInstance().requestPermissions(new Permiso.IOnPermissionResult() {
            @Override
            public void onPermissionResult(Permiso.ResultSet resultSet) {
                if (resultSet.areAllPermissionsGranted()) {

                    // User has permissions
                    getMyGalleryPhotos();

                } else if (resultSet.isPermissionPermanentlyDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                    runOnUiThread(() -> {
                        isLoading(false, false);
                        mNoPhotosText.setText(R.string.missing_per_whrite_storage);
                    });

                    // No permissions or missing permissions
                } else if (resultSet.isPermissionPermanentlyDenied(Manifest.permission.READ_EXTERNAL_STORAGE)){

                    runOnUiThread(() -> {
                        isLoading(false, false);
                        mNoPhotosText.setText(R.string.missing_per_whrite_storage);
                    });

                    // No permissions or missing permissions
                } else if (resultSet.isPermissionPermanentlyDenied(Manifest.permission.CAMERA)){

                    runOnUiThread(() -> {
                        isLoading(false, false);
                        mNoPhotosText.setText(R.string.missing_per_camera);
                    });

                    // No permissions or missing permissions
                } else {

                    runOnUiThread(() -> {
                        isLoading(false, false);
                        mNoPhotosText.setText(R.string.missing_per);
                    });

                }
            }

            @Override
            public void onRationaleRequested(Permiso.IOnRationaleProvided callback, String... permissions) {
                Permiso.getInstance().showRationaleInDialog(null, getString(R.string.msg_permission_required), getString(R.string.ok), callback);
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA);

    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == RESULT_OK)

            if (requestCode == GALLERY_REQUEST_CODE) {//data.getData return the content URI for the selected Image

                Log.v("GALLEY", "GOT NEW PHOTO");

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // Get the cursor
                Cursor cursor = null;
                if (selectedImage != null) {
                    cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                }
                // Move to first row
                if (cursor != null) {
                    cursor.moveToFirst();
                }
                //Get the column index of MediaStore.Images.Media.DATA
                int columnIndex = 0;
                if (cursor != null) {
                    columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                }
                //Gets the String value in the column
                String imgDecodableString = null;
                if (cursor != null) {
                    imgDecodableString = cursor.getString(columnIndex);
                }

                Log.v("GALLEY", "BEFORE NEW PHOTO" + "Size: " + igModelArrayList.size());

                if (imgDecodableString != null && !imgDecodableString.isEmpty()){


                    addNewPhoto(imgDecodableString);
                    Log.v("GALLEY", "ADDED NEW PHOTO" + "Size: " + igModelArrayList.size());

                }


                if (cursor != null) {
                    cursor.close();
                }
                // Set the Image in ImageView after decoding the String

            } else if (requestCode == CAMERA_CODE) {

                ImagePro.ImageDetails imageDetails = imagePro.getImagePath(CAMERA_CODE, RESULT_OK, data);

                // Log.v("CAMERA", "ADDED NEW PHOTO" + "Path: " + imageDetails.getPath());

                if (imageDetails.getPath() != null && !imageDetails.getPath().isEmpty()){

                    addNewPhoto(imageDetails.getPath());

                }

            } else {

                Log.v("CAMERA", "ERROR");
            }
    }

    public void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void takeFromCamera(){

        imagePro.openCameraTaker();
    }

    private void addNewPhoto(String photoPath){

        Log.v("IMAGE", "ADDED NEW PHOTO" + "Path: " + photoPath);

        UploadModel uploadModel = new UploadModel();
        uploadModel.setPhotoPath(photoPath);

        mPhotosUploads.add(2, uploadModel);
        mPhotosUploadAdapter.notifyItemInserted(2);
    }

    private void getMyGalleryPhotos() {

        imagePro = new ImagePro(this);

        igModelArrayList = new ArrayList<>();

        UploadModel camera = new UploadModel();
        camera.setPhotoPath("camera");

        UploadModel gallery = new UploadModel();
        gallery.setPhotoPath("gallery");


        igModelArrayList.add(camera);
        igModelArrayList.add(gallery);

        igModelArrayList.addAll(fetchGalleryImages(this));

        if (igModelArrayList.size() > 0){

            isLoading(false, true);

            mPhotosUploads.clear();
            mPhotosUploads.addAll(igModelArrayList);
            mPhotosUploadAdapter.notifyDataSetChanged();

        } else {

            isLoading(false, false);
            mNoPhotosText.setText(R.string.no_photo_found);
        }

    }

    private ArrayList<UploadModel> fetchGalleryImages(Activity context) {
        ArrayList<UploadModel> galleryImageUrls;
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};//get all columns of type images
        @SuppressLint("InlinedApi") final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date

        Cursor imagecursor = context.managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");//get all data in Cursor by sorting in DESC order

        galleryImageUrls = new ArrayList<>();

        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);//get column index
            //galleryImageUrls.add(imagecursor.getString(dataColumnIndex));//get Image from column index

            UploadModel grModel = new UploadModel();
            grModel.setPhotoPath(imagecursor.getString(dataColumnIndex));

            galleryImageUrls.add(grModel);

        }
        Log.e("fatch in","images");
        return galleryImageUrls;
    }
}
