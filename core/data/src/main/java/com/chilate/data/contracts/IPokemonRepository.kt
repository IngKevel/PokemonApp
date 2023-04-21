package com.chilate.data.contracts

import com.chilate.data.core.RepositoryResponse
import com.chilate.model.Pokemon

/**
 * Created by Kevel on 4/21/2023.
 */
interface IPokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): RepositoryResponse<List<Pokemon>>
}