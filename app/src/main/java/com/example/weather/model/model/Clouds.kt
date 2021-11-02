package com.example.weather.model.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    var all: Int = 0
)