package com.chilate.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.chilate.designsystem.R

/**
 * Created by Kevel on 4/21/2023.
 */

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    url: String?,
    name: String?,
    placeholder: Int,
    imageSize: Dp,
    backgroundColor: Color,
    textColor: Color
) {

    var imageError by remember {
        mutableStateOf(false)
    }

    val initials = name.let { it?.getInitials() }

    Box(
        modifier = modifier
            .size(imageSize)
            .clip(CircleShape)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .background(Color.White),
            model = url,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            error = painterResource(id = placeholder),
            onError = {
                imageError = true
            },
            onSuccess = {
                imageError = false
            }
        )

        if (initials != null && imageError) {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .border(width = 4.dp, color = textColor, shape = CircleShape)
            ) {
                val textSize = imageSize.value / 2
                val textPadding = imageSize.value / 6

                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = textPadding.dp),
                    text = initials,
                    color = textColor,
                    fontSize = textSize.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun String.getInitials(): String? {
    val wordsList = this
        .split(' ')

    var initials: String?

    if (wordsList.size == 1) {
        initials = wordsList[0].first().uppercase()
    } else {
        initials = wordsList[0].first().uppercase() + wordsList[1].first().uppercase()
    }

    if (!initials.all { it.isLetter() }) {
        initials = null
    }

    return initials
}

@Preview(showBackground = true)
@Composable
private fun CircularImagePreview() {
    CircularImage(
        modifier = Modifier,
        url = null,
        name = null,
        placeholder = R.drawable.ic_placeholder,
        imageSize = 60.dp,
        backgroundColor = MaterialTheme.colorScheme.primary,
        textColor = MaterialTheme.colorScheme.onPrimary
    )
}