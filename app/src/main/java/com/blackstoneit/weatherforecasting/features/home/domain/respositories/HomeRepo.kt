package com.blackstoneit.weatherforecasting.features.home.domain.respositories
import com.blackstoneit.domain.home.responses.WeatherResponse
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.domain.responses.CityDirectionResponse
import com.blackstoneit.weatherforecasting.util.models.FinalResponse
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint


interface HomeRepo {
    suspend fun getCityLocation(cityName:String): FinalResponse<ArrayList<CityDirectionResponse>>
    suspend fun getWeatherInfo(latitude:Double,longitude:Double): FinalResponse<WeatherResponse>
    suspend fun getCities():FinalResponse<ArrayList<City>>
}