package com.example.weather.model.repostory

import com.example.weather.model.model.WeatherRoot

interface IWeatherRepostory {
    suspend fun loadWeather(country:String): WeatherRoot

}