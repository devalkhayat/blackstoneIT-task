package com.blackstoneit.weatherforecasting.util.di

import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import com.blackstoneit.weatherforecasting.features.home.domain.usecases.GetWeatherUseCase
import com.blackstoneit.weatherforecasting.util.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit()= RetrofitBuilder()
}