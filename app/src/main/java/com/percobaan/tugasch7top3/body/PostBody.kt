package com.percobaan.tugasch7top3.body


import com.google.gson.annotations.SerializedName

data class PostBody(
    @SerializedName("categories")
    var categories: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("title")
    var title: String
)