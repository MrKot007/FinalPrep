package com.example.session_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session_2.databinding.SpecialItemBinding

class SpecialViewHolder(val binding: SpecialItemBinding) : RecyclerView.ViewHolder(binding.root)
class SpecialsAdapter(val list: List<SpecialItem>) : RecyclerView.Adapter<SpecialViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialViewHolder {
        return SpecialViewHolder(SpecialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SpecialViewHolder, position: Int) {
        holder.binding.textView31.text = "Ad here"
    }
}