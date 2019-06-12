package com.gug.example.posts.repository.splash

import android.app.Application
import com.gug.example.posts.ui.splash.IContractSplash
import com.gug.example.posts.util.PostsPreferences

class SplashRepository(
    val application: Application
) : IContractSplash.Model {

    override fun mustShowAbout(): Boolean {
        val prefs = PostsPreferences(application)
        return prefs.mustShowAbout
    }

}