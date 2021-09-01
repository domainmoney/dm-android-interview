package com.domainmoney.accounts.account_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.domainmoney.common.utils.update
import com.domainmoney.networking.services.AccountApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

@AssistedFactory
interface AccountDetailsViewModelFactory {
    fun create(
        @Assisted("accountId") accountId: Int
    ): AccountDetailsViewModel
}

@OptIn(InternalCoroutinesApi::class)
class AccountDetailsViewModel @AssistedInject constructor(
    @Assisted("accountId") private val accountId: Int,
    private val accountApiService: AccountApiService
) : ViewModel() {

    companion object {
        fun provideFactory(
            assistedFactory: AccountDetailsViewModelFactory,
            accountId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    accountId = accountId
                ) as T
            }
        }
    }

    private val _viewState: MutableStateFlow<AccountDetailsViewState> = MutableStateFlow(AccountDetailsViewState.LoadingState)
    val viewState = _viewState.asStateFlow()

    private val _intentChannel = Channel<AccountDetailsIntent>(Channel.UNLIMITED)
    val intentChannel = _intentChannel

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = accountApiService.getAccount(accountId)
                if (!response.isSuccessful) throw HttpException(response)
                val account = response.body()!!
                _viewState.update { AccountDetailsViewState.DetailsState(account) }
            } catch (ex: Exception) {
                Timber.d(ex)
                _viewState.update { AccountDetailsViewState.ErrorState(ex) }
            }
        }
    }
}
