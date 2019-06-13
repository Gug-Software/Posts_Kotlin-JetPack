package com.gug.example.posts.ui.delete

import androidx.lifecycle.LiveData
import com.gug.example.posts.database.entities.DbPost

interface IContractDeletePosts {

    interface View {
        fun showSnackBar(stringId: Int)
    }

    interface ViewModel {
        fun deleteAll()
    }

    interface Model {

        suspend fun deleteAll()
        fun getAllPosts(): LiveData<List<DbPost>>
    }
}