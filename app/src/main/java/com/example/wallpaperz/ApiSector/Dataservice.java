package com.example.wallpaperz.ApiSector;

import com.example.wallpaperz.Models.CuratedPhoto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Dataservice {
    @Headers({"Accept: Application/json",
            "Authorization: 563492ad6f917000010000010dce40b8db2c47f9a9f99c34efbafdd2"
         })

    @GET("curated/")
    Call<CuratedPhoto> getAllWallpaper(@Query("page")String page,@Query("per_page")String per_page);

}
