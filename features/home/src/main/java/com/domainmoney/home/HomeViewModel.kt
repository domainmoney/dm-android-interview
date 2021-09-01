package com.domainmoney.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

@AssistedFactory
interface HomeViewModelFactory {
    fun create(
        @Assisted("navigateToAccounts") navigateToAccounts: () -> Unit,
    ): HomeViewModel
}

@OptIn(InternalCoroutinesApi::class)
class HomeViewModel @AssistedInject constructor(
    @Assisted("navigateToAccounts") private val navigateToAccounts: () -> Unit,
) : ViewModel() {

    companion object {
        fun provideFactory(
            assistedFactory: HomeViewModelFactory,
            navigateToAccounts: () -> Unit,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    navigateToAccounts = navigateToAccounts,
                ) as T
            }
        }
    }

    private val _viewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState("Test", "User"))
    val viewState = _viewState.asStateFlow()

    private val _intentChannel = Channel<HomeIntent>(Channel.UNLIMITED)
    val intentChannel = _intentChannel

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    HomeIntent.AccountsClicked -> navigateToAccounts()
                }
            }
        }
    }
}
