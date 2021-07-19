package com.domainmoney.interview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.domainmoney.common.navigation.DomainNavigationFactory
import com.domainmoney.common.navigation.GlobalDestination

@Composable
internal fun DomainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = GlobalDestination.Home.route,
    navigationFactories: Set<DomainNavigationFactory>,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigationFactories.forEach { factory ->
            factory.create(this, navController)
        }
    }
}
