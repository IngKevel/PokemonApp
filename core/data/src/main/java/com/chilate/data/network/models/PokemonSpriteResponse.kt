package com.chilate.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevel on 4/21/2023.
 */

data class PokemonSpriteResponse(
    @SerializedName("front_default")
    val front_default: String
)
