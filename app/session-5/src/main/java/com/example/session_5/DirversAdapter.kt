package com.example.session_5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session_5.databinding.DriverItemBinding

class DriverViewHolder(val binding: DriverItemBinding) : RecyclerView.ViewHolder(binding.root)
class DriversAdapter(val list: List<DriverItem>, val onClickDriver: OnClickDriver) : RecyclerView.Adapter<DriverViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        return DriverViewHolder(DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.binding.name.text = list[position].name
        holder.binding.id.text = list[position].id
        holder.binding.root.setOnClickListener {
            onClickDriver.onClick(list[position])
        }
    }
}
interface OnClickDriver{
    fun onClick(driver: DriverItem)
}