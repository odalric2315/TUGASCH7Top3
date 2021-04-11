package com.percobaan.mvvmapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.percobaan.tugasch7top3.repository.RemoteRepository
import com.percobaan.tugasch7top3.view.MyViewModel

class MyViewModelFactory(private val repository: RemoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}