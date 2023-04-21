package com.chilate.data.network

import com.chilate.data.contracts.IPokemonRepository
import com.chilate.data.core.RepositoryResponse
import com.chilate.model.Pokemon
import javax.inject.Inject

/**
 * Created by Kevel on 4/21/2023.
 */

class PokemonApiRepository @Inject constructor(
    private val pokeApi: IPokemonApi
): IPokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): RepositoryResponse<List<Pokemon>> {
        return try {
            val result = pokeApi.getPokemonList(limit = limit, offset = offset)
            if (result.isSuccessful) {
                RepositoryResponse.Success(result.body()?.pokemonList?.map {
                    val details = pokeApi.getPokemonInfo(it.name)
                    it.toPokemonModel(details.body())
                } ?: emptyList())
            } else {
                RepositoryResponse.Error(result.code(), result.errorBody()?.string() ?: "Unknown network error")
            }
        } catch (e: Exception) {
            RepositoryResponse.Error(-1, e.localizedMessage ?: "Unknown error", e)
        }
    }
}