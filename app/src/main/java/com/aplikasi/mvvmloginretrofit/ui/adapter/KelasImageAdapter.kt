package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.ImgHolderViewBinding
import com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData.ImageGallery
import com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData.OneKelasDataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter

class KelasImageAdapter(val id: Int) : RecyclerView.Adapter<KelasImageAdapter.KelasImageHolder>() {

    private var imgKelasList = emptyList<ImageGallery>()

    class KelasImageHolder(binding: ImgHolderViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.imgHolder

        companion object {
            fun from(parent: ViewGroup): KelasImageHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImgHolderViewBinding.inflate(layoutInflater, parent, false)

                return KelasImageHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KelasImageHolder {
        return KelasImageHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return imgKelasList.size
    }

    override fun onBindViewHolder(holder: KelasImageHolder, position: Int) {
        val imgList = imgKelasList[position]

        holder.img.load(Constants.IMAGE_URL + Constants.KELAS_IMAGES + "/" + id + "/" + imgList.image) {
            crossfade(600)
            error(R.drawable.image_icon)
        }
    }

    fun setData(newData: OneKelasDataResponse){
        val dataDiffUtils = DiffUtilsAdapter(imgKelasList, newData.data!!.imageGalleries)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        imgKelasList = newData.data.imageGalleries
        diffUtilResult.dispatchUpdatesTo(this)
    }

}