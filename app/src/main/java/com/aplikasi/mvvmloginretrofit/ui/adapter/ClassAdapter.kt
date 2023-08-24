package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.mvvmloginretrofit.databinding.ClassLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.ClassModels

class ClassAdapter(private val classList: MutableList<ClassModels>) : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    class ClassViewHolder(binding: ClassLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.titleClass
        val stage = binding.stageTitle
        val jmlhVideo = binding.jmlhVideo

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
        return classList.size
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classHolder = classList[position]
        holder.title.text = classHolder.titleClass
        holder.stage.text = classHolder.stage
        holder.jmlhVideo.text = classHolder.jmlhVideo.toString()
    }

}