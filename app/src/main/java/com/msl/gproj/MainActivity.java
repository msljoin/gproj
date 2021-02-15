package com.msl.gproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.msl.gproj.adapter.MovieListAdapter;
import com.msl.gproj.model.Movie;
import com.msl.gproj.viewmodel.MovieList;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<Movie> movieList;
    private MovieListAdapter adapter;
    private MovieList viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noResult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(this, movieList, this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(MovieList.class);

        viewModel.getMoviesListObserver().observe(this, movieModels -> {
            if(movieModels != null) {
                movieList = movieModels;
                adapter.setMovieList(movieModels);
                noResult.setVisibility(View.GONE);
            } else {
                noResult.setVisibility(View.VISIBLE);
            }
        });
        viewModel.getClientCall();

    }

    @Override
    public void onMovieClick(Movie movie) {
        Toast.makeText(this, "Clicked Movie Name is : " +movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}