package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.ViewItemBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    private val selected: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.text1.text = list[position]
        holder.binding.checkBox.isChecked = false
        holder.binding.checkBox.setOnCheckedChangeListener { _, check ->
            if(check) {
                selected.add(holder.adapterPosition)
            } else {
                selected.remove(holder.adapterPosition)
            }
        }
    }

    fun addText(text:String) {
        list.add(text)
        notifyItemInserted(list.lastIndex)
    }

    fun deleteAll() {
        selected.sort()
        for (position in selected.reversed()) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
        selected.clear()
    }

    inner class ItemViewHolder(val binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root)
}