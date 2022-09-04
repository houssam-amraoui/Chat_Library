package com.pam.chat_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    Button start;
    EditText idInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = findViewById(R.id.start);
        idInput = findViewById(R.id.idInput);

        start.setOnClickListener(view -> {
                    if(!idInput.getText().equals("")){
                    Intent usersActivity = new Intent(this,UsersActivity.class);
                    usersActivity.putExtra("token",idInput.getText().toString());
                    startActivity(usersActivity);
                        return;
                    }
                    Toast.makeText(this,"text is empty", Toast.LENGTH_SHORT).show();
        }
                );


    }
}