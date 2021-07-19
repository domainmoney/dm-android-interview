package com.domainmoney.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.Channel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val viewState = viewModel.viewState.collectAsState().value
    val intentChannel = viewModel.intentChannel

    HomeScreen(viewState, intentChannel)
}

@Composable
private fun HomeScreen(
    viewState: HomeViewState,
    intentChannel: Channel<HomeIntent> = Channel()
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = stringResource(id = R.string.txt_welcome),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "${viewState.firstName} ${viewState.lastName}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                ),
                onClick = { intentChannel.trySend(HomeIntent.AccountsClicked) },
            ) {
                Text(text = stringResource(id = R.string.btn_view_accounts))
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.txt_view_accounts),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview("Home")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(HomeViewState("Test", "User"))
}
