package com.blackstoneit.weatherforecasting.util.di

import android.content.Context
import com.blackstoneit.weatherforecasting.features.home.data.repoImp.HomeRepoImpl
import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetCityLocationUseCase
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetWeatherUseCase
import com.blackstoneit.weatherforecasting.util.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCasesModule {
    @Provides
    fun provideGetWeatherUseCase(homeRepo: HomeRepo)= GetWeatherUseCase(homeRepo)

    @Provides
    fun provideGetCityLocationUseCase(homeRepo: HomeRepo)= GetCityLocationUseCase(homeRepo)


}
