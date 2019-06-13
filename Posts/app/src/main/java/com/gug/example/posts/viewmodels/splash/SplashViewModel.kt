package com.gug.example.posts.viewmodels.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gug.example.posts.repository.splash.SplashRepository
import com.gug.example.posts.ui.splash.IContractSplash
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.*

class SplashViewModel(
    application: Application
) : BaseAndroidViewModel(application), IContractSplash.ViewModel {

    private val repository = SplashRepository(application)

    private val _navToAbout = MutableLiveData<Boolean>()
    val navToABout: LiveData<Boolean>
        get() = _navToAbout

    private val _navToPosts = MutableLiveData<Boolean>()
    val navToPosts: LiveData<Boolean>
        get() = _navToPosts

    init {
        uiScope.launch {
            delay(1_000)
        }
        validateShowAbout()
    }

    override fun validateShowAbout() {
        val showAbout = repository.mustShowAbout()
        when (showAbout) {
            true -> _navToPosts.value = true
            else -> _navToAbout.value = true
        }
    }

    fun onAboutNavigatedDone() {
        _navToAbout.value = false
    }

    fun onPostsNavigatedDone() {
        _navToPosts.value = false
    }


}