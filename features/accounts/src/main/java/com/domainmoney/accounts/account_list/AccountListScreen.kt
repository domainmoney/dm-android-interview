package com.domainmoney.accounts.account_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import com.domainmoney.networking.model.AccountModel
import kotlinx.coroutines.channels.Channel

@Composable
fun AccountListScreen(
    viewModel: AccountListViewModel
) {
    val viewState = viewModel.viewState.collectAsState().value
    val intentChannel = viewModel.intentChannel

    AccountListScreen(viewState, intentChannel)
}

@Composable
private fun AccountListScreen(
    viewState: AccountListViewState,
    intentChannel: Channel<AccountListIntent> = Channel()
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = stringResource(id = R.string.txt_account_list),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
            when (viewState) {
                AccountListViewState.LoadingState -> LoadingScreen()
                is AccountListViewState.ListState -> ListScreen(viewState, intentChannel)
                is AccountListViewState.ErrorState -> ErrorScreen(viewState)
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
private fun ListScreen(
    viewState: AccountListViewState.ListState,
    intentChannel: Channel<AccountListIntent>
) {
    Divider()
    LazyColumn {
        items(viewState.accounts) { account ->
            ListElement(
                account = account,
                onClick = { intentChannel.trySend(AccountListIntent.AccountClicked(account)) }
            )
        }
    }
}

@Composable
private fun ListElement(account: AccountModel, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        text = "${account.name} (${account.type})",
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start
    )
    Divider()
}

@Composable
private fun ErrorScreen(
    viewState: AccountListViewState.ErrorState
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

@Preview("AccountList")
@Composable
private fun PreviewAccountListScreen() {
    AccountListScreen(AccountListViewState.LoadingState)
}
