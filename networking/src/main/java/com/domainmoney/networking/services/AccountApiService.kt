package com.domainmoney.networking.services

import com.domainmoney.networking.model.AccountModel
import retrofit2.Response
import retrofit2.http.GET

interface AccountApiService {

    @GET("accounts")
    suspend fun getPlaidLinkToken(): Response<List<AccountModel>>
}
