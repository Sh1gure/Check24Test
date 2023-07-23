package com.shigure.check24.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shigure.check24.R
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.ui.components.state.MainScreenState

@Composable
fun MainScreenDetails(
    screenState: MainScreenState.Details.Data,
    save: () -> Unit,
    delete: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val country = screenState.country
    Column {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.clickable { onBackClicked() })
        Check24AsyncImageBox(
            modifier = Modifier,
            country = country,
            onClick = {}
        )
        Text(
            text = country.name,
            fontSize = 24.sp
        )
        Text(
            text = stringResource(
                id = R.string.main_screen_tile_population,
                country.population
            ),
            fontSize = 18.sp
        )
        val isVisited = country.isSaved
        Button(
            onClick = {
                if (isVisited) {
                    delete()
                } else {
                    save()
                }
            },
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = if (isVisited) "DElete" else "Save",
                fontSize = 24.sp
            )
            Icon(
                painter = painterResource(
                    id = if (isVisited) R.drawable.ic_visited else R.drawable.ic_save
                ),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

    }

}