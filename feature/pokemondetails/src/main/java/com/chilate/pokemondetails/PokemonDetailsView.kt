package com.chilate.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chilate.designsystem.R
import com.chilate.designsystem.components.CircularImage
import kotlin.math.round

/**
 * Created by Kevel on 4/21/2023.
 */

@Composable
fun PokemonDetailsView(
    id: String,
    name: String,
    height: String,
    weight: String,
    firstType: String,
    secondType: String?
) {
    PokemonDetailsContent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        id = id,
        name = name,
        height = height,
        weight = weight,
        firstType = firstType,
        secondType = secondType
    )
}

@Composable
fun PokemonDetailsContent(
    modifier: Modifier = Modifier,
    id: String,
    name: String,
    height: String,
    weight: String,
    firstType: String,
    secondType: String?
) {

    var isFavorite by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularImage(
                modifier = Modifier
                    .padding(8.dp),
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
                name = name,
                placeholder = R.drawable.ic_placeholder,
                imageSize = 160.dp,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "N.$id ${name.uppercase()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

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
            PokemonTypeSection(
                firstType = firstType,
                secondType = secondType
            )
            PokemonDetailDataSection(
                pokemonWeight = weight.toInt(),
                pokemonHeight = height.toInt()
            )
        }
    }
}

@Composable
fun PokemonTypeSection(
    firstType: String,
    secondType: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .clip(CircleShape)
                .height(35.dp)
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = firstType.uppercase(),
                color = Color.White,
                fontSize = 22.sp
            )
        }
        if (secondType != null && secondType != "null") {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .height(35.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = secondType.uppercase(),
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Composable
fun PokemonDetailDataSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100f) / 1000f
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        PokemonDetailDataItem(
            dataValue = pokemonWeightInKg,
            dataUnit = "kg",
            dataIcon = painterResource(id = R.drawable.ic_weight),
            modifier = Modifier
                .weight(1f)
        )

        Spacer(
            modifier = Modifier
                .size(1.dp, sectionHeight)
                .background(Color.LightGray)
        )

        PokemonDetailDataItem(
            dataValue = pokemonHeightInMeters,
            dataUnit = "m",
            dataIcon = painterResource(id = R.drawable.ic_height),
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun PokemonDetailDataItem(
    modifier: Modifier = Modifier,
    dataValue: Float,
    dataUnit: String,
    dataIcon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {

        Icon(
            painter = dataIcon,
            contentDescription = null
        )

        Spacer(modifier = Modifier
            .height(8.dp)
        )

        Text(
            text = "$dataValue$dataUnit"
        )
    }
}