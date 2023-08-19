package com.blackstoneit.weatherforecasting.features.home.data

import com.blackstoneit.domain.home.responses.WeatherResponse
import com.blackstoneit.weatherforecasting.features.home.domain.responses.CityDirectionResponse
import com.blackstoneit.weatherforecasting.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("data/2.5/forecast")
    suspend fun getWeatherInfo(@Query("lat") latitude:Double,@Query("lon") longitude:Double,@Query("units") unit:String="metric",@Query("appid") appID:String=Constants.KEY):Response<WeatherResponse>

    @GET("geo/1.0/direct")
    suspend fun getCityLocation(@Query("q") cityName:String,@Query("appid") appID:String= Constants.KEY):Response<ArrayList<CityDirectionResponse>>
}