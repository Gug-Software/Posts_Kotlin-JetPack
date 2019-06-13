package com.gug.example.posts.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
        NetworkApiStatus.LOADING, NetworkApiStatus.ERROR -> {
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
fun hideFromApiStatus(view: View, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING -> view.visibility = View.GONE
        NetworkApiStatus.ERROR -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("hideFromBoolean")
fun hideFromBoolean(view: View, mustShow: Boolean) {
    when (mustShow) {
        true -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: View, postSize: Int) {
    when (postSize) {
        0 -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("showIfZero")
fun showIfZero(view: View, postSize: Int) {
    when (postSize) {
        0 -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}