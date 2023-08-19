package com.blackstoneit.weatherforecasting.util.di

import android.content.Context
import com.blackstoneit.weatherforecasting.features.home.data.repoImp.HomeRepoImpl
import com.blackstoneit.weatherforecasting.features.home.domain.respositories.HomeRepo
import com.blackstoneit.weatherforecasting.util.RetrofitBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoriesModule {
    @Binds
    abstract  fun provideHomeRepository(imp:HomeRepoImpl):HomeRepo
}