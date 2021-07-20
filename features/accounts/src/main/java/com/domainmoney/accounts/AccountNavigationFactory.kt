package com.domainmoney.accounts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.domainmoney.accounts.account_details.AccountDetailsScreen
import com.domainmoney.accounts.account_details.AccountDetailsViewModelFactory
import com.domainmoney.accounts.account_list.AccountListScreen
import com.domainmoney.accounts.account_list.AccountListViewModelFactory
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
    private val accountListViewModelFactory: AccountListViewModelFactory,
    private val accountDetailsViewModelFactory: AccountDetailsViewModelFactory
) : DomainNavigationFactory {

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(
            route = GlobalDestination.Accounts.route,
            startDestination = AccountDestinations.AccountList.route
        ) {
            composable(route = AccountDestinations.AccountList.route) {
                AccountListScreen(accountListViewModelFactory.create(
                    navigateToAccount = { navController.navigate(AccountDestinations.AccountDetails.create(it.id)) }
                ))
            }
            composable(route = AccountDestinations.AccountDetails.route) {
                val accountId = it.arguments?.getString("accountId")?.toInt()!!
                AccountDetailsScreen(accountDetailsViewModelFactory.create(
                    accountId = accountId
                ))
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
