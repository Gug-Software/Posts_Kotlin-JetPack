package com.gug.example.posts.ui.posts

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gug.example.posts.R
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.databinding.FragmentPostsBinding
import com.gug.example.posts.network.NetworkApiStatus
import com.gug.example.posts.ui.posts.adapter.PostAdapter
import com.gug.example.posts.ui.posts.adapter.PostItemListener
import com.gug.example.posts.viewmodels.posts.PostsViewModel
import com.gug.example.posts.viewmodels.posts.PostsViewModelFactory

class PostsFragment : Fragment() {

    lateinit var postsViewModelFactory: PostsViewModelFactory
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsDataBase: PostsDataBase
    lateinit var application: Application
    lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_posts, container, false
        )

        application = requireNotNull(value = this.activity).application
        postsDataBase = PostsDataBase.getDatabase(application)
        val args = PostsFragmentArgs.fromBundle(arguments!!)
        postsViewModelFactory =
            PostsViewModelFactory(postsDataBase, application, args.showFavorites)
        postsViewModel = ViewModelProviders.of(this, postsViewModelFactory).get(PostsViewModel::class.java)

        binding.viewmodel = postsViewModel
        binding.setLifecycleOwner(this)

        defineObservers()
        configureRecycler()

        return binding.root

    }

    private fun configureRecycler() {

        val adapter = PostAdapter(
            PostItemListener {
                // set as read
                postsViewModel.setPostRead(it)
            }
        )

        binding.postsRecycler.adapter = adapter

        postsViewModel.posts.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun defineObservers() {

        observerSnackBar()

    }

    private fun observerSnackBar() {

//        postsViewModel.showSnackBarEvent.observe(this, Observer {
//            if (it == true) {
//                Snackbar.make(
//                    binding.coordinator,
//                    getString(R.string.msg_deletePosts),
//                    Snackbar.LENGTH_SHORT
//                ).show()
//                postsViewModel.doneShowingSnackbar()
//            }
//        })

        postsViewModel.navToDetailPost.observe(this, Observer { post ->
            post?.let {
                this.findNavController().navigate(
                    PostsFragmentDirections.actionGlobalPostDetailFragment(post)
                )
                postsViewModel.onPostDetailNavigated()
            }
        })

        postsViewModel.status.observe(this, Observer { status ->
            when (status) {
                NetworkApiStatus.LOADING -> binding.postsMsg.text = getString(R.string.msg_apiLoading)
                else -> binding.postsMsg.text = getString(R.string.msg_apiError)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_overflow_posts, menu)
    }


}
