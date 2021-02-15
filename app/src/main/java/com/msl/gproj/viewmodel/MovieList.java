package com.msl.gproj.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.msl.gproj.model.Movie;
import com.msl.gproj.net.Client;
import com.msl.gproj.net.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList extends ViewModel {

    private final MutableLiveData<List<Movie>> moviesList;

    public MovieList() {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMoviesListObserver() {
        return moviesList;
    }

    public void getClientCall() {
        Client client = Service.getInstance().create(Client.class);

        Call<List<Movie>> call = client.getPhotoList();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                moviesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                moviesList.postValue(null);
            }
        });
    }

}
