package com.gug.example.posts.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gug.example.posts.databinding.ListItemPostBinding
import com.gug.example.posts.domain.Post

class PostAdapter(val clickListener: PostItemListener) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(
            parent
        )
    }

    class PostViewHolder private constructor(val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PostItemListener, item: Post) {
            binding.post = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)

                return PostViewHolder(binding)
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
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}

class PostItemListener(
    val clickListener: (post: Post) -> Unit
) {
    fun onClick(post: Post) = clickListener(post)
}
