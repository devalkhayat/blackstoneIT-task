package com.blackstoneit.weatherforecasting.features.home.domain.usecases

import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    suspend operator fun invoke()=homeRepo.getCities()
}