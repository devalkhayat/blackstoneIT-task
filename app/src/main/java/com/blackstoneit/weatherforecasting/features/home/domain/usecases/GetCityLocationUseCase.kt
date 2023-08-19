package com.blackstoneit.weatherforecasting.features.home.domain.usecases

import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import javax.inject.Inject

class GetCityLocationUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    suspend operator fun invoke(cityName:String)=homeRepo.getCityLocation(cityName)
}
