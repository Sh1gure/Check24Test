package com.shigure.check24.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shigure.check24.R
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.ui.components.state.MainScreenState
import com.shigure.check24.ui.components.state.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val screenState by viewModel.uiState.collectAsState()
    MainScreen(
        screenState = screenState,
        refresh = viewModel::refresh,
        onItemClicked = viewModel::onItemClicked,
        onBackPressed = viewModel::onBackPressed,
        save = viewModel::save,
        delete = viewModel::delete
    )
}

@Composable
private fun MainScreen(
    screenState: MainScreenState,
    refresh: () -> Unit,
    onItemClicked: (Country) -> Unit,
    save: () -> Unit,
    delete: () -> Unit,
    onBackPressed: () -> Unit,
) {
    MaterialTheme {
        when (screenState) {
            MainScreenState.Initial.Loading -> {
                InfoScreenSkeleton(message = stringResource(id = R.string.main_screen_info_loading))
            }

            MainScreenState.Initial.Empty -> {
                InfoScreenSkeleton(message = stringResource(id = R.string.main_screen_info_wait))
            }

            is MainScreenState.Content.Error -> {
                InfoScreenSkeleton(
                    message = stringResource(id = R.string.main_screen_info_loading),
                    onRetry = refresh
                )
            }

            is MainScreenState.Content.Data -> {
                MainScreenContent(
                    screenState = screenState,
                    onItemClicked = onItemClicked
                )
            }
            is MainScreenState.Details.Data -> {
                MainScreenDetails(
                    screenState = screenState,
                    save = save,
                    delete = delete,
                    onBackClicked = onBackPressed
                )
            }
        }
    }
}