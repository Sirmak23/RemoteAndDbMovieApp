package com.irmak.remoteanddbmovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.remoteanddbmovieapp.databinding.PopularLayoutBinding
import com.irmak.remoteanddbmovieapp.domain.Movie

class PopularMovieAdapter: RecyclerView.Adapter<PopularMovieViewHolder>() {
    var nowPopList:ArrayList<Movie>? = null

    fun setPopularList(nowPopList:ArrayList<Movie>?){
        this.nowPopList = nowPopList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = PopularLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.PopularBind(nowPopList!!.get(position))
    }

    override fun getItemCount(): Int {
        return nowPopList?.size ?:0
    }
}