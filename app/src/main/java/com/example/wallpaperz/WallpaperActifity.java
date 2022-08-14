package com.example.wallpaperz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wallpaperz.Models.Photo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class WallpaperActifity extends AppCompatActivity {

    Photo photo;
    FloatingActionButton download,setUup;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_actifity);

        download = findViewById(R.id.downloadBtn);
        setUup = findViewById(R.id.setWallpaper);
        imageView = findViewById(R.id.wallpaper);


        photo = (Photo) getIntent().getSerializableExtra("photodata");


        Picasso.get().load(photo.getSrc().getPortrait()).placeholder(R.drawable.ic_baseline_wallpaper_24).into(imageView);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = null;

                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(photo.getSrc().getOriginal());
                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI)
                        .setAllowedOverRoaming(false)
                        .setMimeType("photo/jpg")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setTitle("WallpaperZ_"+photo.getPhotographer())
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,"Wallpaper_"+photo.getPhotographer()+".jpg");

                downloadManager.enqueue(request);
                Toast.makeText(WallpaperActifity.this, "Downloading", Toast.LENGTH_SHORT).show();

            }
        });

        setUup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(WallpaperActifity.this);
                Bitmap bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
                try{

                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(WallpaperActifity.this, "Wallpaper Applied", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(WallpaperActifity.this, "Can't set This Wallpaper", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}