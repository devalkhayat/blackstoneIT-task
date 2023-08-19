package com.blackstoneit.weatherforecasting.features.home.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetCitiesUseCase
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetCityLocationUseCase
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetWeatherUseCase
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeIntents
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeViewStates
import com.blackstoneit.weatherforecasting.util.models.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase,private  val getCityLocationUseCase: GetCityLocationUseCase,private val getCitiesUseCase: GetCitiesUseCase): ViewModel() {

    private  val TAG = "HomeViewModel"
    val intentChannel= Channel<HomeIntents>(Channel.UNLIMITED)

    private val _viewState= MutableStateFlow<HomeViewStates>(HomeViewStates.ideal)
    val state: StateFlow<HomeViewStates> get() = _viewState

    fun processIntent(){
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect{
                when(it){
                    is HomeIntents.Ideal->loading()
                    is HomeIntents.getCities->getCities()
                    is HomeIntents.getCityLocation->getCityLocation(it.cityName)
                    is HomeIntents.getWeatherInfo->getWeatherInfo(it.lat,it.lng)
                }
            }
        }
    }

    private fun loading() {
        _viewState.value = HomeViewStates.ideal
    }


    private fun getCities() {

        viewModelScope.launch(Dispatchers.IO) {
            var rs=getCitiesUseCase()

           when(rs){
               is FinalResponse.Success->{
                   _viewState.value = HomeViewStates.showCities(rs.data)
               }
               is FinalResponse.Error->{
                   _viewState.value = HomeViewStates.showError(rs.exception.message!!)
               }
           }
        }
    }
    private fun getCityLocation(cityName:String) {
        viewModelScope.launch(Dispatchers.IO) {

            var rs = getCityLocationUseCase(cityName)
            when (rs) {
                is FinalResponse.Success -> {
                    _viewState.value = HomeViewStates.showCityLocation(rs.data)
                }

                is FinalResponse.Error -> {
                    _viewState.value = HomeViewStates.showError(rs.exception.message!!)
                }
            }

        }
    }
    private fun getWeatherInfo(lat:Double,lng:Double) {
        viewModelScope.launch(Dispatchers.IO) {
            var rs = getWeatherUseCase(lat, lng)
            when (rs) {
                is FinalResponse.Success -> {

                    _viewState.value = HomeViewStates.showWeatherInfo(rs.data)

                }

                is FinalResponse.Error -> {
                    _viewState.value = HomeViewStates.showError(rs.exception.message!!)
                }
            }
        }
    }



}