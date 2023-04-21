package com.chilate.pokemonlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilate.data.contracts.IPokemonRepository
import com.chilate.data.core.RepositoryResponse
import com.chilate.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kevel on 4/21/2023.
 */

@Singleton
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository
): ViewModel() {

    private var currentPage = 0
    private val pageSize = 25

    private val _pokemonList = mutableStateOf(emptyList<Pokemon>())
    val pokemonList: State<List<Pokemon>>
        get() = _pokemonList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean>
        get() = _isLoading

    private val _isBottom = mutableStateOf(false)
    val isBottom: State<Boolean>
        get() = _isBottom

    fun getPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = pokemonRepository.getPokemonList(limit = pageSize, offset = currentPage * pageSize)) {
                is RepositoryResponse.Error -> {
                    println("Error: ${result.message}")
                }
                is RepositoryResponse.Success -> {
                    _isBottom.value = currentPage * pageSize >= 1281
                    currentPage++
                    _isLoading.value = false
                    _pokemonList.value += result.data
                }
            }
        }
    }
}