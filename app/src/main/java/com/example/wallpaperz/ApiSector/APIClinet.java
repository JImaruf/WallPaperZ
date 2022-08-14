package com.example.wallpaperz.ApiSector;

import android.content.Context;
import android.widget.Toast;

import com.example.wallpaperz.Listeners.CuratedWallpaperListners;
import com.example.wallpaperz.Models.CuratedPhoto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClinet {
    Context context;


    public APIClinet(Context context) {
        this.context = context;
    }



      Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl("https://api.pexels.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    public void getWallper(CuratedWallpaperListners listners, String page) {


        Dataservice dataservice = retrofit.create(Dataservice.class);
        Call<CuratedPhoto> call = dataservice.getAllWallpaper(page, "21");
        call.enqueue(new Callback<CuratedPhoto>() {
            @Override
            public void onResponse(Call<CuratedPhoto> call, Response<CuratedPhoto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }

                listners.onFetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<CuratedPhoto> call, Throwable t) {
                listners.OnError(t.getMessage());
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
