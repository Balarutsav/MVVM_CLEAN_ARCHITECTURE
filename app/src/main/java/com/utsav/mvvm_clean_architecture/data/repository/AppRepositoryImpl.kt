package com.utsav.mvvm_clean_architecture.data.repository

import com.utsav.mvvm_clean_architecture.data.remote.AppApiService
import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import com.utsav.mvvm_clean_architecture.domain.repository.AppRepository
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApiService
) : AppRepository {
    override suspend fun login(request: HashMap<String, String>): Response<LoginDTO> {
        return api.doLogin(request)
    }

}