package com.domainmoney.common.navigation

sealed class GlobalDestination(val route: String) {
    object Home : GlobalDestination("home")
    object Accounts : GlobalDestination("accounts")
}
