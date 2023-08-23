package com.coinedone.youtubeaccessibility

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.coinedone.youtubeaccessibility.databinding.FragmentYoutubeLogBinding


class YoutubeLogRecyclerViewAdapter(
    private val values: List<LogDetailsClass>
) : RecyclerView.Adapter<YoutubeLogRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentYoutubeLogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.channelName
        holder.timeView.text = item.currentTimeStamp
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentYoutubeLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val timeView: TextView = binding.timeStamp

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}