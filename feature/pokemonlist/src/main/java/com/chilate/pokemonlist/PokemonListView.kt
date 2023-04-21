package com.chilate.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chilate.designsystem.components.CircularImage
import com.chilate.designsystem.R
import com.chilate.model.Pokemon
import com.chilate.model.Screen

/**
 * Created by Kevel on 4/21/2023.
 */

@Composable
fun PokemonListView(
    navController: NavHostController,
    viewModel: PokemonListViewModel
) {

    LaunchedEffect(true) {
        viewModel.getPokemonList()
    }

    PokemonListContent(
        modifier = Modifier
            .fillMaxSize(),
        viewModel = viewModel,
        navController = navController,
        loadPokemon = {
            viewModel.getPokemonList()
        }
    )
}

@Composable
fun PokemonListContent(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel,
    navController: NavHostController,
    loadPokemon: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pokemon",
            style = MaterialTheme.typography.titleLarge
        )

        PokemonListComponent(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
            navController = navController,
            viewModel = viewModel,
            loadPokemon = loadPokemon
        )
    }
}

@Composable
fun PokemonListComponent(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel,
    navController: NavHostController,
    loadPokemon: () -> Unit
) {
    val pokemonList = remember {
        viewModel.pokemonList
    }

    val isBottom = remember {
        viewModel.isBottom
    }

    val isLoading = remember {
        viewModel.isLoading
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(pokemonList.value) { pokemon ->
            if(pokemon == pokemonList.value.last() && !isBottom.value && !isLoading.value) {
                loadPokemon.invoke()
            }
            PokemonListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clickable {
                        navController.navigate(
                            Screen.PokemonDetails.route +
                                    "/${pokemon.id}" +
                                    "/${pokemon.name}" +
                                    "/${pokemon.height}" +
                                    "/${pokemon.weight}" +
                                    "/${pokemon.firstType}" +
                                    "/${pokemon.secondType}"
                        )
                    },
                pokemon = pokemon
            )
        }
    }
}

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {

    var isFavorite by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularImage(
                    modifier = Modifier
                        .padding(8.dp),
                    url = pokemon.sprite,
                    name = pokemon.name,
                    placeholder = R.drawable.ic_placeholder,
                    imageSize = 80.dp,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "N.${pokemon.id} ${pokemon.name.uppercase()}",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        isFavorite = !isFavorite
                    },
                painter = painterResource(
                    id = if (isFavorite) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_favorite_border
                    }
                ),
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}