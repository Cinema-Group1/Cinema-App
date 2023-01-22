package com.example.qrscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdminActivity extends AppCompatActivity {

    private Button button_add;
    private Button btn_logout;
    private OkHttpClient client;
    private String url;

    private AlertDialog.Builder dialog_builder;
    private AlertDialog dialog;
    private TextView popup_title, popup_time, movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(v -> {
            logout();
        });
        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(v ->
        {
            addMovie();
        });
        url = "https://cinema-backend-group1.azurewebsites.net/showing/all";
        client = new OkHttpClient();
        movie = findViewById(R.id.textView);
        getURL();

    }

    private void getURL(){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movie.setText(myResponse);
                        }
                    });                }

                    }
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
        popup_title = contentPopup.findViewById(R.id.textView);
        popup_time = contentPopup.findViewById(R.id.textView);

        dialog_builder.setView(contentPopup);
        dialog = dialog_builder.create();
        dialog.show();

    }

}