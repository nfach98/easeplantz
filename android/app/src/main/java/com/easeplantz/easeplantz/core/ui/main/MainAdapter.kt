package com.easeplantz.easeplantz.core.ui.main

import android.content.Intent
import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.easeplantz.easeplantz.core.data.source.local.entity.MainEntity
import com.easeplantz.easeplantz.databinding.ItemsPlantBinding
import com.easeplantz.easeplantz.ui.image.ImageActivity
import com.squareup.picasso.Picasso

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var listData = ArrayList<MainEntity>()
    var onItemClick: ((MainEntity) -> Unit)? = null

    fun setMenus(menu : List<MainEntity>?) {
        if (menu == null) return
        this.listData.clear()
        this.listData.addAll(menu)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemsMainMenuBinding = ItemsPlantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(itemsMainMenuBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val menus = listData[position]
        holder.bind(menus)
    }

    override fun getItemCount(): Int = listData.size

    inner class MainViewHolder(private val binding: ItemsPlantBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(menu: MainEntity) {
            with(binding) {
                Picasso.get().load(menu.image).into(binding.ivPlant)
                tvName.text = menu.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}