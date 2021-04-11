package com.percobaan.tugasch7top3.network

import com.percobaan.tugasch7top3.body.PostBody
import com.percobaan.tugasch7top3.model.PostResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/api/posts")
    fun getPost() : Response<List<PostResponseItem>>

    @POST("/api/posts")
    fun addPost(@Body postBody: PostBody): Response<PostResponseItem>

    @DELETE("/api/posts/5")
    fun deletePost(postBody: PostBody): Response<PostResponseItem>

    @FormUrlEncoded
    @PUT("/api/posts/5")
    fun updatePost(@Body postBody: PostBody): Response<PostResponseItem>
}