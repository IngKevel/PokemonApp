package com.chilate.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevel on 4/21/2023.
 */

data class PokemonDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("sprites")
    val sprites: PokemonSpriteResponse,
    @SerializedName("height")
    val height: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("types")
    val types: List<PokemonTypeResponse>
)
