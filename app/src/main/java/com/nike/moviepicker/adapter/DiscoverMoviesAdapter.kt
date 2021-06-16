package com.nike.moviepicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nike.moviepicker.BuildConfig
import com.nike.moviepicker.data.model.Movie
import com.nike.moviepicker.databinding.ItemMovieCardBinding

class DiscoverMoviesAdapter(
    private val movieList: List<Movie>,
    private val size: Int = movieList.size
) : RecyclerView.Adapter<DiscoverMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position % size]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    class ViewHolder(private val itemBinding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: Movie) {
            itemBinding.movie = movie
            Glide.with(itemBinding.root.context)
                .load(BuildConfig.THEMOVIEDB_IMAGE_URL + movie.poster_path)
                .into(itemBinding.ivMovieImage)
        }
    }
}