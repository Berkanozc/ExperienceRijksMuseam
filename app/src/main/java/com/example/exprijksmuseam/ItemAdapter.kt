package com.example.exprijksmuseam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exprijksmuseam.databinding.ItemGalleryBinding
import com.example.exprijksmuseam.model.ItemInterface

class ItemAdapter(
    private val items: List<ItemInterface>,
    private val clickListener: (ItemInterface) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGalleryBinding.bind(itemView)

        fun dataBind(item: ItemInterface, clickListener: (ItemInterface) -> Unit) {
            binding.tvItemName.text = item.name
            Glide.with(itemView).load(item.imageUrl).into(binding.ivItemImage)

            itemView.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.dataBind(items[position], clickListener)
    }

}