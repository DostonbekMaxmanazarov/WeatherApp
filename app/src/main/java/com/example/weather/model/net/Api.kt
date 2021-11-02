package com.example.weather.model.net

import com.example.weather.model.model.WeatherRoot
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.openweathermap.org/data/2.5/weather?q=toshkent&units=metric&appid=5849aae41830e75a76a36dab467ade51
const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
const val KEY = "5849aae41830e75a76a36dab467ade51"

interface Api {
    @GET("weather")
    suspend fun loadWeather(
        @Query("q") cityName: String,
        @Query("units") temp: String = "metric",
        @Query("appid") appid: String = KEY
    ): WeatherRoot

    companion object {
        operator fun invoke(): Api {

//            val requestInterseptor = Interceptor {
//                val url = it.request()
//                    .url()
//                    .newBuilder()
//                    .addQueryParameter("appid", KEY)
//                    .build()
//
//                val request = it.request()
//                    .newBuilder()
//                    .url(url)
//                    .build()
//
//                return@Interceptor it.proceed(request)
//            }
//            val okHttp = OkHttpClient.Builder()
//                .addInterceptor(requestInterseptor)
//                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(Api::class.java)
        }
    }
}