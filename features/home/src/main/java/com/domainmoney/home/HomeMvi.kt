package com.domainmoney.home

sealed class HomeIntent {
    object AccountsClicked : HomeIntent()
}

data class HomeViewState(
    val firstName: String = "",
    val lastName: String = "",
    val photoUrl: String? = null,
)
