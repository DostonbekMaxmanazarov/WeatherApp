package com.example.weather.presenter

interface IMainActivityPresenter {
    fun loadWeather(countryName: String)

    fun cancel()

}