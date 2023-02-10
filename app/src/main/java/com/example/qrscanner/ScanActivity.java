package com.example.qrscanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;



public class ScanActivity extends AppCompatActivity {

    Button button_scan;
    Button button_logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(v ->{
            logout();
        });
        button_scan = findViewById(R.id.button_scan);
        button_scan.setOnClickListener(v ->
        {
            scanCode();
        });
    }

    // logout button
    private void logout(){
        startActivity(new Intent(ScanActivity.this, MainActivity.class));
    }

    // QR Code scannen
    private void scanCode(){
        // Berechtigungen werden abgefragt
        ScanOptions config = new ScanOptions();
        config.setPrompt("Volume up to flash on");
        config.setBeepEnabled(true);
        config.setOrientationLocked(true);
        config.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(config);

    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
            builder.setTitle("Result");
            String content = result.getContents();
            // Prüfung, ob QR Code in Rep liegt
            if (content.startsWith("https://cinema-backend-group1.azurewebsites.net/ticket/check:")){
                builder.setMessage("Scan war erfolgreich!");
            }else{
                // QR Code stellt kein valides Ticket dar
                builder.setMessage("QR Code ist ungültig!");
            }
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();

            }
    });


}