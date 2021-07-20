package com.domainmoney.accounts.account_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
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
import com.domainmoney.accounts.R
import kotlinx.coroutines.channels.Channel

@Composable
fun AccountDetailsScreen(
    viewModel: AccountDetailsViewModel
) {
    val viewState = viewModel.viewState.collectAsState().value
    val intentChannel = viewModel.intentChannel

    AccountDetailsScreen(viewState, intentChannel)
}

@Composable
private fun AccountDetailsScreen(
    viewState: AccountDetailsViewState,
    intentChannel: Channel<AccountDetailsIntent> = Channel()
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (viewState) {
                AccountDetailsViewState.LoadingState -> LoadingScreen()
                is AccountDetailsViewState.DetailsState -> DetailsScreen(viewState, intentChannel)
                is AccountDetailsViewState.ErrorState -> ErrorScreen(viewState)
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.txt_loading),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center
    )
    CircularProgressIndicator()
}

@Composable
private fun DetailsScreen(
    viewState: AccountDetailsViewState.DetailsState,
    intentChannel: Channel<AccountDetailsIntent>
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = viewState.account.name,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Start
    )
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = viewState.account.type,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun ErrorScreen(
    viewState: AccountDetailsViewState.ErrorState
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.txt_error),
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center
    )
    Text(
        text = viewState.exception.message ?: stringResource(id = R.string.txt_error_unknown),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview("AccountDetails")
@Composable
private fun PreviewAccountDetailsScreen() {
    AccountDetailsScreen(AccountDetailsViewState.LoadingState)
}
