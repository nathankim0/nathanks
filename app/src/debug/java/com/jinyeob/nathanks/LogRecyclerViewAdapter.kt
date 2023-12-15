package com.jinyeob.nathanks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jinyeob.nathanks.common.onThrottleClick
import com.jinyeob.nathanks.databinding.LogRecyclerviewContentBinding
import dagger.hilt.android.scopes.ActivityScoped
import java.io.File
import javax.inject.Inject

@ActivityScoped
class LogRecyclerViewAdapter @Inject constructor() :
    ListAdapter<File, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LogViewHolder(
            LogRecyclerviewContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LogViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class LogViewHolder(private val binding: LogRecyclerviewContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(file: File) {
            binding.textView.text = file.name
            binding.root.onThrottleClick {
                onItemListener?.onItemClick(file)
            }
            binding.root.setOnLongClickListener {
                onItemListener?.onItemLongClick(file)
                true
            }
        }
    }

    private var onItemListener: OnItemListener? = null

    interface OnItemListener {
        fun onItemClick(item: File)
        fun onItemLongClick(item: File)
    }

    fun setOnItemListener(listener: OnItemListener) {
        onItemListener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<File>() {
        override fun areItemsTheSame(
            oldItem: File,
            newItem: File
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: File,
            newItem: File
        ) = oldItem.hashCode() == newItem.hashCode()
    }
}
