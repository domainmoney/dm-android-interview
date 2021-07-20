package com.domainmoney.accounts.account_list

import com.domainmoney.networking.model.AccountModel
import java.lang.Exception

sealed class AccountListIntent {
    data class AccountClicked(val account: AccountModel) : AccountListIntent()
}

sealed class AccountListViewState {
    object LoadingState : AccountListViewState()
    data class ListState(val accounts: List<AccountModel>) : AccountListViewState()
    data class ErrorState(val exception: Exception) : AccountListViewState()
}
