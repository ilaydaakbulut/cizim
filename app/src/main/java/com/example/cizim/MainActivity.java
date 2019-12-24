package com.example.cizim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import java.util.UUID;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button save;
    cizim cizim_eleman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.save_button);
        cizim_eleman = findViewById(R.id.cizim);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                //kullanıcıdan izin alma işlemleri yapılır.
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.save_button){
                    AlertDialog.Builder saveDialog = new AlertDialog.Builder(MainActivity.this);
                    saveDialog.setTitle("save?");
                    saveDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cizim_eleman.setDrawingCacheEnabled(true);//çizimi etkinleştirir
                            String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(), cizim_eleman.getDrawingCache(), UUID.randomUUID().toString()+".png", "drawing");
                            if (imgSaved != null){
                                Toast savedToast = Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG);
                                savedToast.show();
                            }
                            else {
                                Toast unSaved=Toast.makeText(getApplicationContext(),"opps eror",Toast.LENGTH_LONG);
                                unSaved.show();
                            }
                        }
                    });
                    saveDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    saveDialog.show();
                }
            }
        });
    }
}

