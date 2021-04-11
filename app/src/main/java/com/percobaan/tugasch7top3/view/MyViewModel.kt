package com.percobaan.tugasch7top3.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.percobaan.tugasch7top3.model.PostResponseItem
import com.percobaan.tugasch7top3.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private val repository: RemoteRepository) : ViewModel() {
    private val _data = MutableLiveData<List<PostResponseItem>>()
    private val _error = MutableLiveData<String>()

    val getData : LiveData<List<PostResponseItem>> = _data
    val getError : LiveData<String> = _error


    fun getPost(){
        viewModelScope.launch(Dispatchers.Main){
            repository.getPost({
            _data.value = it
            },{
            _error.value = it
            })
        }
    }

}