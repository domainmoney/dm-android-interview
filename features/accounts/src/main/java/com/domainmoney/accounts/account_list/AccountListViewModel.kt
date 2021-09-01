package com.domainmoney.accounts.account_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.domainmoney.common.utils.update
import com.domainmoney.networking.model.AccountModel
import com.domainmoney.networking.services.AccountApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

@AssistedFactory
interface AccountListViewModelFactory {
    fun create(
        @Assisted("navigateToAccount") navigateToAccount: (AccountModel) -> Unit,
    ): AccountListViewModel
}

@OptIn(InternalCoroutinesApi::class)
class AccountListViewModel @AssistedInject constructor(
    @Assisted("navigateToAccount") private val navigateToAccount: (AccountModel) -> Unit,
    private val accountApiService: AccountApiService
) : ViewModel() {

    companion object {
        fun provideFactory(
            assistedFactory: AccountListViewModelFactory,
            navigateToAccount: (AccountModel) -> Unit,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    navigateToAccount = navigateToAccount,
                ) as T
            }
        }
    }

    private val _viewState: MutableStateFlow<AccountListViewState> = MutableStateFlow(AccountListViewState.LoadingState)
    val viewState = _viewState.asStateFlow()

    private val _intentChannel = Channel<AccountListIntent>(Channel.UNLIMITED)
    val intentChannel = _intentChannel

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = accountApiService.getAccounts()
                if (!response.isSuccessful) throw HttpException(response)
                val accounts = response.body()!!
                _viewState.update { AccountListViewState.ListState(accounts) }
            } catch (ex: Exception) {
                Timber.d(ex)
                _viewState.update { AccountListViewState.ErrorState(ex) }
            }
        }

        viewModelScope.launch(Dispatchers.Main) {
            _intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is AccountListIntent.AccountClicked -> navigateToAccount(intent.account)
                }
            }
        }
    }
}
