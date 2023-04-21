package com.chilate.model

/**
 * Created by Kevel on 4/21/2023.
 */
data class Pokemon(
    val id: String,
    val name: String,
    val sprite: String,
    val height: String,
    val weight: String,
    val firstType: String,
    val secondType: String?
)