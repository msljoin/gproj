package com.msl.gproj.net;

import com.msl.gproj.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Client {

    @GET("/photos")
    Call<List<Movie>> getPhotoList();
}
