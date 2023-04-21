package com.chilate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chilate.model.Screen
import com.chilate.pokemondetails.PokemonDetailsView
import com.chilate.pokemonlist.PokemonListView
import com.chilate.pokemonlist.PokemonListViewModel

/**
 * Created by Kevel on 4/21/2023.
 */

@Composable
fun Navigation(
    navController: NavHostController,
    pokemonListViewModel: PokemonListViewModel
) {

    NavHost(navController = navController, startDestination = Screen.PokemonList.route) {

        composable(route = Screen.PokemonList.route) {
            PokemonListView(
                navController = navController,
                viewModel = pokemonListViewModel
            )
        }

        composable(route = Screen.PokemonDetails.route + "/{id}/{name}/{height}/{weight}/{firstType}/{secondType}",
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
            },
            navArgument(name = "name") {
                type = NavType.StringType
            },
            navArgument(name = "height") {
                type = NavType.StringType
            },
            navArgument(name = "weight") {
                type = NavType.StringType
            },
            navArgument(name = "firstType") {
                type = NavType.StringType
            },
            navArgument(name = "secondType") {
                type = NavType.StringType
            }
        )
        ) {
            val id = it.arguments?.getString("id") ?: ""
            val name = it.arguments?.getString("name") ?: "Default"
            val height = it.arguments?.getString("height") ?: "0"
            val weight = it.arguments?.getString("weight") ?: "0"
            val firstType = it.arguments?.getString("firstType") ?: "Default"
            val secondType = it.arguments?.getString("secondType")

            PokemonDetailsView(
                id = id,
                name = name,
                height = height,
                weight = weight,
                firstType = firstType,
                secondType = secondType
            )
        }
    }
}