package com.gug.example.posts.util

import android.content.Context

const val FILE_NAME = "PostsPrefs"
const val SHOW_ABOUT = "showAbout"

class PostsPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    var mustShowAbout: Boolean
        get() = prefs.getBoolean(SHOW_ABOUT, true)
        set(value) = prefs.edit().putBoolean(SHOW_ABOUT, value).apply()
}