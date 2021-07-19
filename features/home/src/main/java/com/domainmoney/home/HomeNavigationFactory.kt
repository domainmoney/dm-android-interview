package com.domainmoney.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.domainmoney.common.navigation.DomainNavigationFactory
import com.domainmoney.common.navigation.GlobalDestination
import com.domainmoney.common.navigation.HiltNavigationFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Singleton

@HiltNavigationFactory
class HomeNavigationFactory @Inject constructor(
    private val homeViewModelFactory: HomeViewModelFactory
) : DomainNavigationFactory {

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable(route = GlobalDestination.Home.route) {
            HomeScreen(homeViewModelFactory.create(
                navigateToAccounts = { navController.navigate(GlobalDestination.Accounts.route) },
            ))
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface HomeNavigationFactoryModule {
    @Singleton
    @Binds
    @IntoSet
    fun bindNavigationFactory(factory: HomeNavigationFactory): DomainNavigationFactory
}
