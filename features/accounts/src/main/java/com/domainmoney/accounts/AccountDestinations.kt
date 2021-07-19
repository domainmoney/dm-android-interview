package com.domainmoney.accounts

sealed class AccountDestinations(val route: String) {
    object AccountList : AccountDestinations("accounts/list")
}
