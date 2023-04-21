package com.chilate.data.di

import com.chilate.data.network.IPokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kevel on 4/21/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()

    @Provides
    @Singleton
    fun providePokemonApi (retrofit: Retrofit): IPokemonApi = retrofit.create(IPokemonApi::class.java)
}