package com.example.multiplepermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String[] permissions;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.request);
        permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!hasPermissions(MainActivity.this, permissions)) {
                        ActivityCompat.requestPermissions( MainActivity.this,permissions, 4);

                }


            }
        });
    }


        private boolean hasPermissions (Context context, String... permissions){
            if (context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
            return true;
        }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==4){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "External storage ok", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "external permission denied", Toast.LENGTH_SHORT).show();
            }
            if (grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera pemission ok", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
}