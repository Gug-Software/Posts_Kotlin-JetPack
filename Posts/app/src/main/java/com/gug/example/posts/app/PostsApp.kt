package com.gug.example.posts.app

import android.app.Application
import timber.log.Timber

class PostsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}