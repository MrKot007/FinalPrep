package com.example.session_4

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session_4.databinding.TransactionItemBinding

class TransactionViewHolder(val binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root)
class TransactionsAdapter(var list: List<Transaction>) : RecyclerView.Adapter<TransactionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.binding.transactionDate.text = list[position].date
        holder.binding.transactionName.text = list[position].name
        if (list[position].negative) {
            holder.binding.sum.text = "-N${list[position].sum}.00"
            holder.binding.sum.setTextColor(Color.rgb(237, 58, 58))
        }else {
            holder.binding.sum.text = "N${list[position].sum}.00"
            holder.binding.sum.setTextColor(Color.rgb(53, 179, 105))
        }
    }
    private fun setData(data: List<Transaction>) {
        this.list = data
        notifyDataSetChanged()
    }
}