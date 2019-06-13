package com.gug.example.posts.ui.posts.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.gug.example.posts.R
import com.gug.example.posts.domain.Post

@BindingAdapter("readFormatted")
fun TextView.readFormatted(post: Post?) {
    post?.let {
        text = when (post.read) {
            true -> context.resources.getString(R.string.text_readOk)
            else -> context.resources.getString(R.string.text_readNo)
        }
    }
}

@BindingAdapter("setFavoriteImage")
fun setFavoriteImage(view: ImageView, post: Post?) {
    val color = getAssociatedColor(post, view.context)
    ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color as Int))
    view.setImageDrawable(
        getDrawablePopularity(
            post,
            view.context
        )
    )
}

fun getDrawablePopularity(post: Post?, context: Context): Drawable? {
    return when (post?.favorite) {
        true -> ContextCompat.getDrawable(context, R.drawable.ic_favorite_black_24dp)
        else -> ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp)
    }
}

fun getAssociatedColor(post: Post?, context: Context): Any {
    return when (post?.favorite) {
        true -> ContextCompat.getColor(context, android.R.color.holo_red_light)
        else -> ContextCompat.getColor(context, android.R.color.black)
    }
}

@BindingAdapter("setReadImage")
fun ImageView.setReadImage(post: Post?) {
    post?.let {
        setImageResource(
            when (post.read) {
                true -> R.drawable.ic_label_black_24dp
                else -> R.drawable.ic_label_outline_black_24dp
            }
        )
    }
}