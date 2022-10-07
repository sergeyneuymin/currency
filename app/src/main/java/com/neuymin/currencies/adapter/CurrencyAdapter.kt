package com.neuymin.currencies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neuymin.currencies.model.Record
import com.neuymin.currencies.R


class CurrencyAdapter(): RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    private var currList = mutableListOf<Record>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_val: TextView = itemView.findViewById(R.id.curr_list_item_val)
        val txt_date: TextView = itemView.findViewById(R.id.curr_list_item_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = currList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_val.text = currList[position].value
        holder.txt_date.text = currList[position].date
    }

    fun update(item: Record) {
        currList.add(currList.size, item);
        notifyItemInserted(currList.size)

    }


}