package com.example.android.shopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shopping.contracts.OnItemUpdated
import com.example.android.shopping.database.ShoppingItem
import com.example.android.shopping.databinding.ShoppingItemBinding


class ShoppinngListAdapter(private val onItemUpdated: OnItemUpdated) :
    ListAdapter<ShoppingItem, ShoppinngListAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ShoppingItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemBinding.addImg.setOnClickListener {
            currentItem.amount++
            onItemUpdated.update(currentItem)
        }
        holder.itemBinding.removeImg.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                onItemUpdated.update(currentItem)
            }
        }

        holder.itemBinding.trash.setOnClickListener {
            onItemUpdated.delete(currentItem)
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.amount == newItem.amount
        }

    }


    class ViewHolder(var itemBinding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(shoppingItem: ShoppingItem) {
            itemBinding.item = shoppingItem
        }
    }
}