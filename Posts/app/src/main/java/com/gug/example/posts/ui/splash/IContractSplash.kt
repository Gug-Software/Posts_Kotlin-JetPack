package com.gug.example.posts.ui.splash

interface IContractSplash {

    interface View {
        fun navigateToAbout()
        fun navigateToPosts()
    }

    interface ViewModel {
        fun validateShowAbout()
    }

    interface Model {
        fun mustShowAbout(): Boolean
    }
}