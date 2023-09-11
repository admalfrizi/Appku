package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.ClassLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.response.kelasData.Data
import com.aplikasi.mvvmloginretrofit.model.response.kelasData.KelasDataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants.IMAGE_URL
import com.aplikasi.mvvmloginretrofit.util.Constants.KELAS_IMAGES
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter

class ClassAdapter : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    private var kelasList = emptyList<Data>()
    class ClassViewHolder(binding: ClassLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.titleClass
        val stage = binding.stageTitle
        val jmlhVideo = binding.jmlhVideo
        val imgClass = binding.classImg

        companion object {
            fun from(parent: ViewGroup): ClassViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClassLayoutBinding.inflate(layoutInflater, parent, false)

                return ClassViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        return ClassViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return kelasList.size
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classHolder = kelasList[position]
        holder.title.text = classHolder.titleKelas
        holder.stage.text = classHolder.stage
        holder.jmlhVideo.text = classHolder.jmlhVideo.toString()

        if(classHolder.imageGalleries[0].image.isNullOrEmpty()){
            holder.imgClass.setImageResource(R.drawable.your_class)
        } else {
            holder.imgClass.load(IMAGE_URL + KELAS_IMAGES + classHolder.id + "/" + classHolder.imageGalleries[0].image) {
                crossfade(600)
                error(R.drawable.group_btn)
            }
        }

        Log.d("RecyclerView", IMAGE_URL + KELAS_IMAGES + classHolder.id + "/" + classHolder.imageGalleries[0].image)
    }

    fun setData(items: KelasDataResponse){
        val dataDiffUtils = DiffUtilsAdapter(kelasList, items.data)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        kelasList = items.data
        diffUtilResult.dispatchUpdatesTo(this)
    }

}