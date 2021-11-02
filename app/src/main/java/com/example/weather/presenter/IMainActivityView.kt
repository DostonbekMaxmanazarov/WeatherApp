package com.example.weather.presenter

import com.example.weather.model.model.WeatherRoot

interface IMainActivityView {

    fun sendWeather(weatherRoot: WeatherRoot)

    fun onFail()

    fun showRefresh()

    fun hideRefresh()

}