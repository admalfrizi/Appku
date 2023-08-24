package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.mvvmloginretrofit.databinding.TileLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.WebinarModels

class TileAdapter(
    private val webinarList: MutableList<WebinarModels>
) : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {

    class TileViewHolder(binding: TileLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val tgl = binding.tglWebinar
        val title = binding.titleWebinar
        val jam = binding.clockWebinar
        val freeOrBuy = binding.freeOrBuy

        companion object {
            fun from(parent: ViewGroup): TileViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TileLayoutBinding.inflate(layoutInflater, parent, false)

                return TileViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        return TileViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        val webinar = webinarList[position]
        holder.jam.text = webinar.clockWebinar
        holder.tgl.text = webinar.tglWebinar
        holder.title.text = webinar.titleWebinar
        holder.freeOrBuy.text = webinar.freeOrBuy
    }

    override fun getItemCount(): Int {
        return webinarList.size
    }
}