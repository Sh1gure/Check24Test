package com.shigure.check24.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
fun MainScreenContent(
    screenState: MainScreenState.Content.Data,
    onItemClicked: (Country) -> Unit,
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(24.dp),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = screenState.countries) {
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { onItemClicked(it) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (it.isCarSideRight) {
                        if (it.isSaved) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_visited),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                text = it.name,
                                fontSize = 24.sp
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.main_screen_tile_population,
                                    it.population
                                ),
                                fontSize = 18.sp
                            )
                        }
                        Check24AsyncImageBox(
                            modifier = Modifier.size(54.dp),
                            country = it,
                            onClick = { }
                        )
                    } else {
                        Check24AsyncImageBox(
                            modifier = Modifier.size(54.dp),
                            country = it,
                            onClick = { }
                        )
                        Column {
                            Text(
                                text = it.name,
                                fontSize = 24.sp
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.main_screen_tile_population,
                                    it.population
                                ),
                                fontSize = 18.sp
                            )
                        }
                        if (it.isSaved) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_visited),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                }

            }
            item {

            }
        }

    }

}