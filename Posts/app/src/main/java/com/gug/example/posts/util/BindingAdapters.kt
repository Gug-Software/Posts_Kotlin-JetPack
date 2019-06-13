package com.gug.example.posts.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gug.example.posts.network.NetworkApiStatus

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("postsApiStatus")
fun postsApiStatus(statusTextView: TextView, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
        }
        NetworkApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        NetworkApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
    }
}

@BindingAdapter("showFromApiStatus")
fun showFromApiStatus(progressBar: ProgressBar, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("hideFromApiStatus")
fun hideFromApiStatus(recyclerView: RecyclerView, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING -> recyclerView.visibility = View.GONE
        NetworkApiStatus.ERROR -> recyclerView.visibility = View.GONE
        else -> recyclerView.visibility = View.VISIBLE
    }
}