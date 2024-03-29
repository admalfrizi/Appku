package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.NewsTileLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.response.newsData.Data
import com.aplikasi.mvvmloginretrofit.model.response.newsData.NewsDataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsTileViewHolder>() {

    private var newsList = emptyList<Data>()
    class NewsTileViewHolder(binding: NewsTileLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val tgl = binding.tglNews
        val title = binding.titleNews
        val jam = binding.clockNews
        val imgNews = binding.newsImg

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
        val newsList = newsList[position]
        holder.jam.text = getparsedDateToTime(newsList.createdAt!!)
        holder.tgl.text = getparsedDate(newsList.createdAt)
        holder.title.text = newsList.nameNews
        if(newsList.imageGalleries.isEmpty()){
            holder.imgNews.setImageResource(R.drawable.image_icon)
        } else {
            holder.imgNews.load(Constants.IMAGE_URL + Constants.NEWS_IMAGES + newsList.id + "/" + newsList.imageGalleries[0].image) {
                crossfade(600)
                error(R.drawable.image_icon)
            }
        }
    }

    @Throws(Exception::class)
    private fun getparsedDate(date: String): String? {
        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",  Locale.US)
        var parseData: String? = null
        val d: Date
        try {
            d = sdf.parse(date)!!
            parseData = SimpleDateFormat("EEEE dd MMM", Locale("id")).format(d)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return parseData
    }

    @Throws(Exception::class)
    private fun getparsedDateToTime(date: String): String? {
        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        var parseData: String? = null
        val d: Date
        try {
            sdf.timeZone = TimeZone.getTimeZone("ICT")
            d = sdf.parse(date)!!
            parseData = SimpleDateFormat("HH:mm", Locale("id")).format(d)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return parseData
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(newData: NewsDataResponse){
        val dataDiffUtils = DiffUtilsAdapter(newsList, newData.data)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        newsList = newData.data
        diffUtilResult.dispatchUpdatesTo(this)
    }
}