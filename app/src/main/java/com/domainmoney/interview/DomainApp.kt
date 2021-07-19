package com.domainmoney.interview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.domainmoney.common.navigation.DomainNavigationFactory
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun DomainApp(navigationFactories: Set<DomainNavigationFactory>) {
    ProvideWindowInsets {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
        }

        val navController = rememberNavController()
        DomainNavGraph(
            navController = navController,
            navigationFactories = navigationFactories,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
        )
    }
}
