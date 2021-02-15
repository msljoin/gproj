package com.msl.gproj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.msl.gproj.R;
import com.msl.gproj.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private ItemClickListener clickListener;

    public MovieListAdapter(Context context, List<Movie> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle());

        holder.itemView.setOnClickListener(v -> clickListener.onMovieClick(movieList.get(position)));

        Picasso.get()
                .load(this.movieList.get(position).getUrl())
                .resize(200,200)
                .centerCrop()
                .into(holder.imageView);
//
//        Glide.with(context)
//                .load()
//                .apply(RequestOptions.centerCropTransform())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.movieList != null) {
            return this.movieList.size();
        }
        return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public interface ItemClickListener{
        void onMovieClick(Movie movie);
    }
}
