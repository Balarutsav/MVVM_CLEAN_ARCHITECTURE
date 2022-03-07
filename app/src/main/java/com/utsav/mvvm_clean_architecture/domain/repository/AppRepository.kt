package com.utsav.mvvm_clean_architecture.domain.repository

import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import retrofit2.Response

interface AppRepository {
    suspend fun login(request: HashMap<String, String>): Response<LoginDTO>
}