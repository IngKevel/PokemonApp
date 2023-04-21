package com.chilate.data.di

import com.chilate.data.contracts.IPokemonRepository
import com.chilate.data.network.PokemonApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Kevel on 4/21/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePokemonRepository(implementation: PokemonApiRepository): IPokemonRepository
}