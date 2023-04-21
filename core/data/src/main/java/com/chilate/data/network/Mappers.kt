package com.chilate.data.network

import com.chilate.data.network.models.PokemonDetailsResponse
import com.chilate.data.network.models.PokemonListResponseItem
import com.chilate.model.Pokemon

/**
 * Created by Kevel on 4/21/2023.
 */

fun PokemonListResponseItem.toPokemonModel(detailsResponse: PokemonDetailsResponse?): Pokemon {
    return Pokemon(
        id = detailsResponse?.id ?: "0",
        name = name,
        sprite = detailsResponse?.sprites?.front_default ?: "",
        height = detailsResponse?.height ?: "0.0",
        weight = detailsResponse?.weight ?: "0.0",
        firstType = detailsResponse?.types?.first()?.type?.name ?: "",
        secondType = if (detailsResponse?.types?.size == 1) null else detailsResponse?.types?.last()?.type?.name
    )
}