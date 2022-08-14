package com.example.wallpaperz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperz.Adapters.WallpaperAdapter;
import com.example.wallpaperz.ApiSector.APIClinet;
import com.example.wallpaperz.ApiSector.Dataservice;
import com.example.wallpaperz.Listeners.CuratedWallpaperListners;
import com.example.wallpaperz.Listeners.onRecyclerClick;
import com.example.wallpaperz.Models.CuratedPhoto;
import com.example.wallpaperz.Models.Photo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements onRecyclerClick {

    RecyclerView myRecycler;
    List<Photo> mylist;
    ProgressDialog progressDialog;
    APIClinet apiClinet;
    int page;
    FloatingActionButton back,forward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecycler = findViewById(R.id.recyclerView);
        back = findViewById(R.id.backward);
        forward = findViewById(R.id.forward);
        progressDialog = new ProgressDialog(this);
      progressDialog.setTitle("Loading...");
        progressDialog.show();


        apiClinet = new APIClinet(this);
        apiClinet.getWallper(listners,"1");

        if(page<=1)
        {
            back.setVisibility(View.GONE);
        }

        
        
        
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "back page", Toast.LENGTH_SHORT).show();
                if(page>1)
                {
                    back.setVisibility(View.VISIBLE);
                    String back_page= String.valueOf(page-1);
                    apiClinet.getWallper(listners,back_page);

                }
                else if(page==1)
                {
                    back.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "This is the first Page", Toast.LENGTH_SHORT).show();

                }



            }
        });


        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Toast.makeText(MainActivity.this, "next page", Toast.LENGTH_SHORT).show();
                String next_page = String.valueOf(page+1);
                apiClinet.getWallper(listners,next_page);
                back.setVisibility(View.VISIBLE);
            }
        });


    }

     public final CuratedWallpaperListners listners = new CuratedWallpaperListners() {
        @Override
        public void onFetch(CuratedPhoto resoponse, String message) {

            progressDialog.dismiss();
            if(resoponse.getPhotos().isEmpty())
            {
                Toast.makeText(MainActivity.this, "No Image Found", Toast.LENGTH_SHORT).show();
            }
           page =  resoponse.getPage();
            ShowData(resoponse.getPhotos());

        }

        @Override
        public void OnError(String message) {

            Toast.makeText(MainActivity.this, message+"", Toast.LENGTH_SHORT).show();

        }
    };

    private void ShowData(List<Photo> photos) {

        myRecycler = findViewById(R.id.recyclerView);
        myRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
       WallpaperAdapter  wallpaperAdapter = new WallpaperAdapter(MainActivity.this,photos,this);
       myRecycler.setAdapter(wallpaperAdapter);



    }

    @Override
    public void onClick(Photo photo) {

        Toast.makeText(this, photo.getPhotographer()+ "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,WallpaperActifity.class);
        intent.putExtra("photodata",photo);
        startActivity(intent);

    }
    
    
    
}