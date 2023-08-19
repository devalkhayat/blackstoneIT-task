package com.blackstoneit.weatherforecasting.features.home.domain.usecases

import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import javax.inject.Inject


class GetWeatherUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    suspend operator fun invoke(latitude:Double, longitude :Double)=homeRepo.getWeatherInfo(latitude, longitude )
}
