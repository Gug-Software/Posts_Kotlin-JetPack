package com.gug.example.posts.ui.delete


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.gug.example.posts.R
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.databinding.FragmentDeletePostsBinding
import com.gug.example.posts.viewmodels.delete.DeletePostsViewModel
import com.gug.example.posts.viewmodels.delete.DeletePostsViewModelFactory
import com.gug.example.posts.viewmodels.posts.PostsViewModel
import com.gug.example.posts.viewmodels.posts.PostsViewModelFactory

class DeletePostsFragment : Fragment() {

    lateinit var vmFactory: DeletePostsViewModelFactory
    lateinit var deletePostsViewModel: DeletePostsViewModel
    lateinit var postsDataBase: PostsDataBase
    lateinit var application: Application
    lateinit var binding: FragmentDeletePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_delete_posts, container, false
        )

        application = requireNotNull(value = this.activity).application
        postsDataBase = PostsDataBase.getDatabase(application)
        vmFactory = DeletePostsViewModelFactory(postsDataBase, application)
        deletePostsViewModel = ViewModelProviders.of(this, vmFactory).get(DeletePostsViewModel::class.java)

        binding.viewmodel = deletePostsViewModel
        binding.setLifecycleOwner(this)

        return binding.root

    }


}
