package com.irmak.remoteanddbmovieapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.irmak.remoteanddbmovieapp.databinding.PopularLayoutBinding
import com.irmak.remoteanddbmovieapp.domain.Movie
import com.irmak.remoteanddbmovieapp.extensions.loadImage

class PopularMovieViewHolder(val binding: PopularLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun PopularBind(response:Movie){
        binding.posterViewUP.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.upComingImageBackground.loadImage("https://www.themoviedb.org/t/p/w1280_and_h720_bestv2${response.backdrop_path}")
        binding.imdbPhotoUP.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtDateUP.text = response.release_date
        binding.txtTitleUP.text = response.title
        binding.txtVoteAvarageUP.text = "10"
        if (response.overview.isNullOrEmpty().not()){
            binding.txtOverViewUP.text = response.overview
        }
    }
}