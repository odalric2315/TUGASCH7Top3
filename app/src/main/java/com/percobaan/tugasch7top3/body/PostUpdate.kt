package com.percobaan.tugasch7top3.body


import com.google.gson.annotations.SerializedName

data class PostUpdate(
    @SerializedName("categories")
    var categories: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?
)