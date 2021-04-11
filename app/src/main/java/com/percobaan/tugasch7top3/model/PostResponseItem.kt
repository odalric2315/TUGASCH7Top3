package com.percobaan.tugasch7top3.model


import com.google.gson.annotations.SerializedName

data class PostResponseItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("categories")
    val categories: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("title")
    val title: String
)