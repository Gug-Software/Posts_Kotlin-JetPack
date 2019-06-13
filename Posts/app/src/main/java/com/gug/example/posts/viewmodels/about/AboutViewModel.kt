package com.gug.example.posts.viewmodels.about

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gug.example.posts.repository.about.AboutRepository
import com.gug.example.posts.ui.about.IContractAbout
import com.gug.example.posts.viewmodels.BaseAndroidViewModel

class AboutViewModel(
    application: Application
) : BaseAndroidViewModel(application), IContractAbout.ViewModel {

    private val repository = AboutRepository(application)

    private val _navToPosts = MutableLiveData<Boolean>()
    val navToPosts: LiveData<Boolean>
        get() = _navToPosts

    private val _showOption = MutableLiveData<Boolean>()
    val showOption: LiveData<Boolean>
        get() = _showOption

    override fun setShowAbout(showAbout: Boolean) {
        repository.setShowAbout(showAbout)
    }

    fun navigateToPosts() {
        _navToPosts.value = true
    }

    fun onPostsNavigatedDone() {
        _navToPosts.value = false
    }

    fun setShowOption(showOption: Boolean) {
        _showOption.value = showOption
    }

}