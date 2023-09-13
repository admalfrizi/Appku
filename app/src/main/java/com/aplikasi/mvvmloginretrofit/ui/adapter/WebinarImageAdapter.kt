package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.ImgHolderViewBinding
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData.ImageGallery
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData.OneWebinarDataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter

class WebinarImageAdapter(val id: Int) : RecyclerView.Adapter<WebinarImageAdapter.WebinarImageHolder>() {
    private var imgWebinarList = emptyList<ImageGallery>()

    class WebinarImageHolder(binding: ImgHolderViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.imgHolder

        companion object {
            fun from(parent: ViewGroup): WebinarImageHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImgHolderViewBinding.inflate(layoutInflater, parent, false)

                return WebinarImageHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarImageHolder {

        return WebinarImageHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return imgWebinarList.size
    }

    override fun onBindViewHolder(holder: WebinarImageHolder, position: Int) {
        val imgList = imgWebinarList[position]

        holder.img.load(Constants.IMAGE_URL + Constants.WEBINAR_IMAGES + "/" + id + "/" + imgList.image) {
            crossfade(600)
            error(R.drawable.image_icon)
        }
    }

    fun setData(newData: OneWebinarDataResponse){
        val dataDiffUtils = DiffUtilsAdapter(imgWebinarList, newData.data!!.imageGalleries)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        imgWebinarList = newData.data.imageGalleries
        diffUtilResult.dispatchUpdatesTo(this)
    }

}