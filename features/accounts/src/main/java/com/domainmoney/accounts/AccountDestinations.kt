package com.domainmoney.accounts

sealed class AccountDestinations(val route: String) {
    object AccountList : AccountDestinations("accounts/list")
    object AccountDetails : AccountDestinations("accounts/{accountId}") {
        fun create(accountId: Int) = "accounts/$accountId"
    }
}
