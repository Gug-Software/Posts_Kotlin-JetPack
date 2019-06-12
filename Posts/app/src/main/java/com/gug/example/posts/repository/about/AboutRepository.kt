package com.gug.example.posts.repository.about

import android.app.Application
import com.gug.example.posts.ui.about.IContractAbout
import com.gug.example.posts.util.PostsPreferences

class AboutRepository(
    val application: Application
) : IContractAbout.Model {

    override fun setShowAbout(showAbout: Boolean) {
        val prefs = PostsPreferences(application)
        prefs.mustShowAbout = showAbout
    }

}