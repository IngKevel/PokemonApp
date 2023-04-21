package com.chilate.model

/**
 * Created by Kevel on 4/21/2023.
 */

sealed class Screen(val route: String) {
    object PokemonList: Screen("pokemon_list")
    object PokemonDetails: Screen("pokemon_details")
}