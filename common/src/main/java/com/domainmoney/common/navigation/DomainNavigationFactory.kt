package com.domainmoney.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface DomainNavigationFactory {
    fun create(builder: NavGraphBuilder, navController: NavHostController)
}
