package com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states

import com.blackstoneit.domain.home.responses.WeatherResponse
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.domain.responses.CityDirectionResponse
import com.blackstoneit.weatherforecasting.util.models.FinalResponse

sealed class HomeViewStates {

    object ideal: HomeViewStates()
    data class showError(val message:String): HomeViewStates()
    data class showCities(val result: ArrayList<City>): HomeViewStates()
    data class showCityLocation(val result: ArrayList<CityDirectionResponse>): HomeViewStates()
    data class showWeatherInfo(val result: WeatherResponse): HomeViewStates()

}
