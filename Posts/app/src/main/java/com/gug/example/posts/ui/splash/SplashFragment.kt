package com.gug.example.posts.ui.splash

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
import com.gug.example.posts.databinding.FragmentSplashBinding
import com.gug.example.posts.viewmodels.splash.SplashViewModel
import com.gug.example.posts.viewmodels.splash.SplashViewModelFactory

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var viewModel: SplashViewModel
    lateinit var viewModelFactory: SplashViewModelFactory
    lateinit var application: Application

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash, container, false
        )

        application = requireNotNull(value = this.activity).application
        viewModelFactory = SplashViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)

        defineObservers()

        return binding.root
    }

    private fun defineObservers() {

        viewModel.navToABout.observe(this, Observer { about ->
            if (about) {
                this.findNavController().navigate(
                    SplashFragmentDirections.actionToAbout()
                )
                viewModel.onAboutNavigatedDone()
            }
        })

        viewModel.navToPosts.observe(this, Observer { posts ->
            if (posts) {
                this.findNavController().navigate(
                    SplashFragmentDirections.actionToPosts()
                )
                viewModel.onPostsNavigatedDone()
            }
        })
    }

}
