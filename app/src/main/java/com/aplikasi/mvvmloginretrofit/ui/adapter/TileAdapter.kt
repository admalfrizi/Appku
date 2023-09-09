package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.mvvmloginretrofit.databinding.TileLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.Data
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.DataResponse
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TileAdapter : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {

    private var webinarList = emptyList<Data>()
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        val webinar = webinarList[position]


        holder.jam.text = getparsedDateToTime(webinar.created_at!!)
        holder.tgl.text = getparsedDate(webinar.created_at)
        holder.title.text = webinar.titleWebinar
        holder.freeOrBuy.text = webinar.freeOrBuy
    }

    @Throws(Exception::class)
    private fun getparsedDate(date: String): String? {
        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",  Locale.US)
        var s2: String? = null
        val d: Date
        try {
            d = sdf.parse(date)!!
            s2 = SimpleDateFormat("EEEE dd MMM", Locale("id")).format(d)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return s2
    }

    @Throws(Exception::class)
    private fun getparsedDateToTime(date: String): String? {
        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        var s2: String? = null
        val d: Date
        try {
            sdf.timeZone = TimeZone.getTimeZone("ICT")
            d = sdf.parse(date)!!
            s2 = SimpleDateFormat("HH:mm", Locale("id")).format(d)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return s2
    }

    override fun getItemCount(): Int {
        return webinarList.size
    }

    fun setData(newData: DataResponse){
        val dataDiffUtils = DiffUtilsAdapter(webinarList, newData.data)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        webinarList = newData.data
        diffUtilResult.dispatchUpdatesTo(this)
    }
}