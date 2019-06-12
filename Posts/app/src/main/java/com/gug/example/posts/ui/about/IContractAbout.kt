package com.gug.example.posts.ui.about

interface IContractAbout {

    interface View {
        fun navigateToPosts()
    }

    interface ViewModel {
        fun setShowAbout(showAbout: Boolean)
    }

    interface Model {
        fun setShowAbout(showAbout: Boolean)
    }
}