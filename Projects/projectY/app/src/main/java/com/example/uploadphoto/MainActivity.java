package com.example.uploadphoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnOpenCamera;
    public static final String MY_IMAGE = "myImage";
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageview);
        btnOpenCamera = findViewById(R.id.btnOpenCamera);
        preferences = getSharedPreferences(MY_IMAGE, MODE_PRIVATE);

        String imageUrl = preferences.getString("image", "");
        Glide.with(this).load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);

        }

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            Glide.with(this).load(captureImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView);
            editor = getSharedPreferences(MY_IMAGE, MODE_PRIVATE).edit();
            editor.putString("image", getImageUri(this, captureImage));
            editor.apply();

            File filePath = Environment.getExternalStorageDirectory();
            File dir = new File(filePath.getAbsolutePath() + "/Photos/");
            dir.mkdir();

            File file = new File(dir, System.currentTimeMillis() + ".jpg");

            try {
                outputStream = new FileOutputStream(file);
                captureImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "image", null);
        return path;
    }
}