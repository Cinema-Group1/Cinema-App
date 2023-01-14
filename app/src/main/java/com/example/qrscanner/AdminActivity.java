package com.example.qrscanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private Button button_add;
    private Button btn_logout;

    private AlertDialog.Builder dialog_builder;
    private AlertDialog dialog;
    private TextView popup_title, popup_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(v ->{
            logout();
        });
        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(v ->
        {
            addMovie();
        });
    }

    private void logout(){
        startActivity(new Intent(AdminActivity.this, MainActivity.class));
    }

    private void addMovie(){
        createPopup();
    }

    public void createPopup(){
        dialog_builder = new AlertDialog.Builder(this);
        final View contentPopup = getLayoutInflater().inflate(R.layout.activity_admin, null);
        popup_title = contentPopup.findViewById(R.id.textView4);
        popup_time = contentPopup.findViewById(R.id.textView5);

        dialog_builder.setView(contentPopup);
        dialog = dialog_builder.create();
        dialog.show();

    }

}