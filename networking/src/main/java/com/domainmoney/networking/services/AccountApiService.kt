package com.domainmoney.networking.services

import com.domainmoney.networking.model.AccountModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApiService {
    @GET("accounts")
    suspend fun getAccounts(): Response<List<AccountModel>>

    @GET("accounts/{accountId}")
    suspend fun getAccount(@Path("accountId") accountId: Int): Response<AccountModel>
}
