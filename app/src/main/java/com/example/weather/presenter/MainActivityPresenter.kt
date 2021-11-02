package com.example.weather.presenter

import com.example.weather.model.repostory.IWeatherRepostory
import com.example.weather.model.repostory.WeatherRepostory
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivityPresenter(private val view: IMainActivityView) : IMainActivityPresenter,
    CoroutineScope {
    private val repository: IWeatherRepostory = WeatherRepostory()

    private val mainJob = SupervisorJob()

    override fun loadWeather(countryName: String) {

        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val weatherTemp = withContext(Dispatchers.IO) {
                    repository.loadWeather(countryName)
                }
                view.sendWeather(weatherTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun cancel() {
        mainJob.cancel()
    }

    override val coroutineContext: CoroutineContext = mainJob
}