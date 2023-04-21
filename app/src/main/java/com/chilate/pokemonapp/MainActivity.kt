package com.chilate.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.chilate.designsystem.theme.PokeAppTheme
import com.chilate.navigation.Navigation
import com.chilate.pokemonlist.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var pokemonListViewModel: PokemonListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                val navHost = rememberNavController()

                Navigation(
                    navController = navHost,
                    pokemonListViewModel = pokemonListViewModel
                )
            }
        }
    }
}