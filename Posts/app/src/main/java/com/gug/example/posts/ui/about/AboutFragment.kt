package com.gug.example.posts.ui.about


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gug.example.posts.R
import com.gug.example.posts.databinding.FragmentAboutBinding
import com.gug.example.posts.viewmodels.about.AboutViewModel
import com.gug.example.posts.viewmodels.about.AboutViewModelFactory

class AboutFragment : Fragment() {

    lateinit var binding: FragmentAboutBinding
    lateinit var viewModel: AboutViewModel
    lateinit var viewModelFactory: AboutViewModelFactory
    lateinit var application: Application

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_about, container, false
        )

        application = requireNotNull(value = this.activity).application
        viewModelFactory = AboutViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AboutViewModel::class.java)

        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)

        defineObservers()

        return binding.root

    }

    private fun defineObservers() {

        viewModel.navToPosts.observe(this, Observer { posts ->
            if (posts) {
                this.findNavController().navigate(
                    AboutFragmentDirections.actionToPosts()
                )
                viewModel.onPostsNavigatedDone()
            }
        })

    }


}
