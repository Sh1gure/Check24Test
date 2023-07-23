package com.shigure.check24.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shigure.check24.R
import com.shigure.check24.functionality.entity.Country

@Composable
fun Check24AsyncImageBox(
    modifier: Modifier,
    country: Country,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.flag)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.im_placeholder),
            contentDescription = stringResource(R.string.main_screen_info_country),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable {
                    onClick()
                }
        )

    }
}