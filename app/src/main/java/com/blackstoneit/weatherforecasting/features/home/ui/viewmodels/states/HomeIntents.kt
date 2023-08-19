package com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states

sealed class HomeIntents{
    object Ideal: HomeIntents()
    object getCities: HomeIntents()
    data class getCityLocation(var cityName:String): HomeIntents()
    data class getWeatherInfo(var lat:Double,var lng:Double): HomeIntents()
}
