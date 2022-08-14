package com.example.wallpaperz.Listeners;

import com.example.wallpaperz.Models.CuratedPhoto;

public interface CuratedWallpaperListners {

    public void onFetch(CuratedPhoto resoponse,String message);
    void OnError(String message);
}
