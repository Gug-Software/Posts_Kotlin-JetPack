package com.gug.example.posts.ui.detail

import android.app.Application
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gug.example.posts.R
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.databinding.FragmentPostDetailBinding
import com.gug.example.posts.domain.Post
import com.gug.example.posts.ui.detail.adapter.CommentAdapter
import com.gug.example.posts.viewmodels.detail.PostDetailViewModel
import com.gug.example.posts.viewmodels.detail.PostDetailViewModelFactory

class PostDetailFragment : Fragment() {

    lateinit var postDetailViewModelFactory: PostDetailViewModelFactory
    lateinit var postDetailViewModel: PostDetailViewModel
    lateinit var postsDataBase: PostsDataBase
    lateinit var application: Application
    lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_post_detail, container, false
        )

        application = requireNotNull(value = this.activity).application
        postsDataBase = PostsDataBase.getDatabase(application)
        val args = PostDetailFragmentArgs.fromBundle(arguments!!)
        postDetailViewModelFactory = PostDetailViewModelFactory(
            database = postsDataBase, application = application, post = args.post
        )
        postDetailViewModel = ViewModelProviders.of(
            this,
            postDetailViewModelFactory
        ).get(
            PostDetailViewModel::class.java
        )

        binding.viewmodel = postDetailViewModel
        binding.setLifecycleOwner(this)
        binding.executePendingBindings()

        defineObservers()
        configureRecycler()

        return binding.root
    }

    private fun defineObservers() {

        postDetailViewModel.currentPost.observe(this, Observer {
            val color = getAssociatedColor(it, context)
            ImageViewCompat.setImageTintList(binding.imageButton, ColorStateList.valueOf(color as Int))
            binding.imageButton.setImageDrawable(
                getDrawablePopularity(
                    it,
                    context
                )
            )
        })

    }

    fun getDrawablePopularity(post: Post?, context: Context?): Drawable? {
        return when (post?.favorite) {
            true -> ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_black_24dp)
            else -> ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_border_black_24dp)
        }
    }

    fun getAssociatedColor(post: Post?, context: Context?): Any {
        return when (post?.favorite) {
            true -> ContextCompat.getColor(context!!, android.R.color.holo_red_light)
            else -> ContextCompat.getColor(context!!, android.R.color.black)
        }
    }

    private fun configureRecycler() {

        val adapter = CommentAdapter()

        binding.commentsRecycler.adapter = adapter

        postDetailViewModel.comments.observe(this, Observer {
            adapter.submitList(it)
        })
    }


}

