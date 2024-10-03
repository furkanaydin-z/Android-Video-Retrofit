package com.fako.videoexam.uı.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fako.videoexam.databinding.CardViewBinding
import com.fako.videoexam.entity.Movies
import com.fako.videoexam.uı.fragment.MainFragmentDirections

class MovieAdapter(var mContext:Context, var movieList: List<Movies>)
    :RecyclerView.Adapter<MovieAdapter.CardDesignKeeper>() {

    inner class CardDesignKeeper(var design : CardViewBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignKeeper {
        var binding = CardViewBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardDesignKeeper(binding)
    }

    override fun onBindViewHolder(holder: CardDesignKeeper, position: Int) {
        val list = movieList.get(position)
        val m = holder.design

        val url = "http://kasimadalan.pe.hu/filmler_yeni/resimler/${list.resim}"
        Glide.with(mContext).load(url).override(300,550).into(m.imageView2)

        m.textName.text = list.ad
        m.textPrice.text = list.fiyat.toString()
        m.card.setOnClickListener {
            val movi = MainFragmentDirections.actionMainFragmentToDetailFragment(movie = list)
            Navigation.findNavController(it).navigate(movi)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}