package com.chilate.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevel on 4/21/2023.
 */

data class PokemonTypeResponseItem(
    @SerializedName("name")
    val name: String
)
