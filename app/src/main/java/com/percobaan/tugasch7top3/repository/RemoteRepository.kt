package com.percobaan.tugasch7top3.repository

import com.percobaan.tugasch7top3.model.PostResponseItem
import com.percobaan.tugasch7top3.network.ApiService

class RemoteRepository (private val apiService: ApiService) {
        fun getPost(
            onResult: (List<PostResponseItem>?) -> Unit,
            onError: (String) -> Unit
        ) {
            val response = apiService.getPost()
            if (response.isSuccessful){
                onResult(response.body())
            }else{
                onError(response.message())
            }
        }
}