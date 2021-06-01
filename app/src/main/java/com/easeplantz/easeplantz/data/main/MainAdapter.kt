package com.easeplantz.easeplantz.data.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easeplantz.easeplantz.data.MainEntity
import com.easeplantz.easeplantz.databinding.ItemsPlantBinding
import com.easeplantz.easeplantz.ui.OptionActivity

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var listMenus = ArrayList<MainEntity>()

    fun setMenus(menu : List<MainEntity>?) {
        if (menu == null) return
        this.listMenus.clear()
        this.listMenus.addAll(menu)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemsMainMenuBinding = ItemsPlantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(itemsMainMenuBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val menus = listMenus[position]
        holder.bind(menus)
    }

    override fun getItemCount(): Int =listMenus.size

    class MainViewHolder(private val binding: ItemsPlantBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(menu: MainEntity) {
        with(binding) {
            tvName.text = menu.title
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, OptionActivity::class.java)
                intent.putExtra(OptionActivity.EXTRA_OPTION,menu.id)
                itemView.context.startActivity(intent)
            }

        }
    }
    }

}