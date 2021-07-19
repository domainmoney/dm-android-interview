package com.domainmoney.accounts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.domainmoney.accounts.account_list.AccountListScreen
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
class AccountsNavigationFactory @Inject constructor(
) : DomainNavigationFactory {

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(
            route = GlobalDestination.Accounts.route,
            startDestination = AccountDestinations.AccountList.route
        ) {
            composable(route = AccountDestinations.AccountList.route) {
                AccountListScreen()
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface AccountsNavigationFactoryModule {
    @Singleton
    @Binds
    @IntoSet
    fun bindNavigationFactory(factory: AccountsNavigationFactory): DomainNavigationFactory
}
