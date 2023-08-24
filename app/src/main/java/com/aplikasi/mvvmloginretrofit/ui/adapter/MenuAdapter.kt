package com.aplikasi.mvvmloginretrofit.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.aplikasi.mvvmloginretrofit.databinding.MenuRowLayoutBinding
import com.aplikasi.mvvmloginretrofit.model.MenuList

class MenuAdapter(
    private val context: Context,
    private val dataSource: List<MenuList>
) : BaseAdapter() {

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder

        if (convertView == null) {
            val itemBinding = MenuRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

            holder = ViewHolder(itemBinding)
            holder.view = itemBinding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.binding.menuIcon.setImageResource(dataSource[position].imgMenu)
        holder.binding.menuTitle.text = dataSource[position].titleMenu
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, dataSource[position].settingScr)

            intent.putExtra("titleMenu", dataSource[position].titleMenu)

            context.startActivity(intent)
        }

        return holder.view
    }


    inner class ViewHolder(binding: MenuRowLayoutBinding) {
        var view: View
        val binding: MenuRowLayoutBinding

        init {
            view = binding.root
            this.binding = binding
        }
    }


}