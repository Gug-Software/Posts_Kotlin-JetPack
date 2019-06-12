package com.gug.example.posts.viewmodels.about

import android.app.Application
import com.gug.example.posts.repository.about.AboutRepository
import com.gug.example.posts.ui.about.IContractAbout
import com.gug.example.posts.viewmodels.BaseAndroidViewModel

class AboutViewModel(
    application: Application
) : BaseAndroidViewModel(application), IContractAbout.ViewModel {

    private val repository = AboutRepository(application)

    override fun setShowAbout(showAbout: Boolean) {
        repository.setShowAbout(showAbout)
    }

}