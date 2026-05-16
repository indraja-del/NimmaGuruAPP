package com.example.nimmaguru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PollAdapter(private val list: ArrayList<PollModel>) :
    RecyclerView.Adapter<PollAdapter.PollViewHolder>() {

    class PollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectText: TextView = itemView.findViewById(R.id.subjectText)
        val countText: TextView = itemView.findViewById(R.id.countText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poll, parent, false)
        return PollViewHolder(view)
    }

    override fun onBindViewHolder(holder: PollViewHolder, position: Int) {

        val item = list[position]

        holder.subjectText.text = item.subject
        holder.countText.text = "Interested Students: ${item.count}"
    }

    override fun getItemCount(): Int = list.size
}