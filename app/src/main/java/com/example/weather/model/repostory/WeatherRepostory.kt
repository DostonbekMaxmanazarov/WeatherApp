package com.example.weather.model.repostory

import com.example.weather.model.model.WeatherRoot
import com.example.weather.model.net.Api

class WeatherRepostory(private val api: Api) : IWeatherRepostory {

    override suspend fun loadWeather(country: String): WeatherRoot = api.loadWeather(country)

    companion object {
        private var instance: WeatherRepostory? = null
        operator fun invoke(): IWeatherRepostory {
            var localIns = instance
            if (localIns == null) {
                val api = Api()
                localIns = WeatherRepostory(api)
                instance = localIns
            }
            return localIns
        }
    }
}