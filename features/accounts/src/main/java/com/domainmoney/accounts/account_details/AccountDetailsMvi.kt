package com.domainmoney.accounts.account_details

import com.domainmoney.networking.model.AccountModel

sealed class AccountDetailsIntent {
    data class AccountClicked(val account: AccountModel) : AccountDetailsIntent()
}

sealed class AccountDetailsViewState {
    object LoadingState : AccountDetailsViewState()
    data class DetailsState(val account: AccountModel) : AccountDetailsViewState()
    data class ErrorState(val exception: Exception) : AccountDetailsViewState()
}
