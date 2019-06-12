package com.gug.example.posts.viewmodels.splash

import android.app.Application
import com.gug.example.posts.repository.splash.SplashRepository
import com.gug.example.posts.ui.splash.IContractSplash
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.*

class SplashViewModel(
    application: Application
) : BaseAndroidViewModel(application), IContractSplash.ViewModel {

    private val repository = SplashRepository(application)

    var test = false

    init {
        uiScope.launch {
            delay(1_000)
        }
        validateShowAbout()
    }

    override fun validateShowAbout() {
        val showAbout = repository.mustShowAbout()
        when (showAbout) {
            true -> test = true
            else -> test = true
        }
    }


}