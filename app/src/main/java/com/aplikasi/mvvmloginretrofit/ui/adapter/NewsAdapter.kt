package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.mvvmloginretrofit.databinding.NewsTileLayoutBinding
import com.aplikasi.mvvmloginretrofit.databinding.TileLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.WebinarModels
import com.aplikasi.mvvmloginretrofit.model.response.NewsModels

class NewsAdapter(
    private val webinarList: MutableList<NewsModels>
) : RecyclerView.Adapter<NewsAdapter.NewsTileViewHolder>() {

    class NewsTileViewHolder(binding: NewsTileLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val tgl = binding.tglNews
        val title = binding.titleNews
        val jam = binding.clockNews

        companion object {
            fun from(parent: ViewGroup): NewsTileViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsTileLayoutBinding.inflate(layoutInflater, parent, false)

                return NewsTileViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTileViewHolder {
        return NewsTileViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsTileViewHolder, position: Int) {
        val webinar = webinarList[position]
        holder.jam.text = webinar.jamRilis
        holder.tgl.text = webinar.tglRilis
        holder.title.text = webinar.titleNews
    }

    override fun getItemCount(): Int {
        return webinarList.size
    }
}