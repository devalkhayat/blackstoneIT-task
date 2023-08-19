package com.blackstoneit.weatherforecasting.features.home.data.repoImp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.blackstoneit.weatherforecasting.util.RetrofitBuilder
import com.blackstoneit.weatherforecasting.features.home.data.EndPoints
import com.blackstoneit.domain.home.responses.WeatherResponse
import com.blackstoneit.weatherforecasting.R
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.domain.responses.CityDirectionResponse
import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import com.blackstoneit.weatherforecasting.util.models.FinalResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



class HomeRepoImpl @Inject constructor(retrofitBuilder: RetrofitBuilder): HomeRepo {

    private  val TAG = "HomeRepoImpl"

    private val endPoints=retrofitBuilder.start()?.create(EndPoints::class.java)

    override suspend fun getCityLocation(cityName: String): FinalResponse<ArrayList<CityDirectionResponse>> {
        try {

            val result =  endPoints?.getCityLocation(cityName)

            result.let {

                when (it?.code()) {
                    //Success
                    200 ->  {
                        return FinalResponse.Success(result?.body()!!)
                    }

                    else -> {
                        return FinalResponse.Error(Exception(result?.message()))
                    }
                }

            }


        }catch (ex:Exception){

            return FinalResponse.Error(ex)
        }

    }

    override suspend fun getWeatherInfo(latitude: Double, longitude: Double): FinalResponse<WeatherResponse> {

        try {

            val result =  endPoints?.getWeatherInfo(latitude,longitude)

            result.let {

                when (it?.code()) {
                    //Success
                    200 ->  {
                        return FinalResponse.Success(result?.body()!!)
                    }
                    else -> {
                        return FinalResponse.Error(Exception(result?.message()))
                    }
                }

            }


        }catch (ex:Exception){

            return FinalResponse.Error(ex)
        }
    }

    override suspend fun getCities(): FinalResponse<ArrayList<City>> {
        try {
            val cities=ArrayList<City>()
            cities.add(City("Alexandria"))
            cities.add(City("Cairo"))
            cities.add(City("Sharm El-Sheikh"))

            return FinalResponse.Success(cities)
        }catch (ex:Exception){
            return FinalResponse.Error(ex)
        }
    }


}