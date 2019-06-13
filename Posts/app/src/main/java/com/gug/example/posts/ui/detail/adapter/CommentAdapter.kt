package com.gug.example.posts.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gug.example.posts.databinding.ListItemCommentBinding
import com.gug.example.posts.domain.Comment

class CommentAdapter() : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentDiffCallback()) {

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.from(
            parent
        )
    }

    class CommentViewHolder private constructor(val binding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.comment = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CommentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCommentBinding.inflate(layoutInflater, parent, false)

                return CommentViewHolder(binding)
            }
        }
    }

}


/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}