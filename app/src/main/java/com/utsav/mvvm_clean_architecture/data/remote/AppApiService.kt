package com.utsav.mvvm_clean_architecture.data.remote

import com.utsav.mvvm_clean_architecture.common.ApiConstant
import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import retrofit2.Response
import retrofit2.http.*

interface AppApiService {

    @FormUrlEncoded
    @POST(ApiConstant.API_LOGIN)
    suspend fun doLogin(@FieldMap maps: Map<String, String>): Response<LoginDTO>
}