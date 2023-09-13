package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.TileLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData.Data
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData.WebinarDataResponse
import com.aplikasi.mvvmloginretrofit.ui.details.WebinarDetailScreen
import com.aplikasi.mvvmloginretrofit.util.Constants.IMAGE_URL
import com.aplikasi.mvvmloginretrofit.util.Constants.WEBINAR_IMAGES
import com.aplikasi.mvvmloginretrofit.util.DiffUtilsAdapter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TileAdapter(val context: Context) : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {

    private var webinarList = emptyList<Data>()
    class TileViewHolder(binding: TileLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val tgl = binding.tglWebinar
        val title = binding.titleWebinar
        val jam = binding.clockWebinar
        val imgWebinar = binding.webinarImg
        val freeOrBuy = binding.freeOrBuy
        val toDetails = binding.root

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
        val webinarList = webinarList[position]
        val parseClock = getparsedDateToTime(webinarList.created_at!!)
        val parseDate = getparsedDate(webinarList.created_at)

        holder.jam.text = parseClock
        holder.tgl.text = parseDate
        holder.title.text = webinarList.titleWebinar
        holder.freeOrBuy.text = webinarList.freeOrBuy
        if(webinarList.imageGalleries.isEmpty()){
            holder.imgWebinar.setImageResource(R.drawable.image_icon)
        } else {
            holder.imgWebinar.load(IMAGE_URL + WEBINAR_IMAGES + "/" + webinarList.id + "/" + webinarList.imageGalleries[0].image) {
                crossfade(600)
                error(R.drawable.image_icon)
            }
        }

        holder.toDetails.setOnClickListener {
            val intent = Intent(context, WebinarDetailScreen::class.java)
            intent.putExtra("id", webinarList.id)
            intent.putExtra( "name",webinarList.titleWebinar)
            intent.putExtra( "freeOrBuy",webinarList.freeOrBuy)
            intent.putExtra("jam", parseClock)
            intent.putExtra("tgl", parseDate)

            context.startActivity(intent)
        }

        Log.d("RecyclerViewWebinars", IMAGE_URL + WEBINAR_IMAGES + "/" + webinarList.id + "/" + webinarList.imageGalleries[0].image)
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

    fun setData(newData: WebinarDataResponse){
        val dataDiffUtils = DiffUtilsAdapter(webinarList, newData.data)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtils)

        webinarList = newData.data
        diffUtilResult.dispatchUpdatesTo(this)
    }
}