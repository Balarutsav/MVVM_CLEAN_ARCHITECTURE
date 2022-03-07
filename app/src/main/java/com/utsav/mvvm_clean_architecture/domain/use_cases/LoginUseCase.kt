package com.utsav.mvvm_clean_architecture.domain.use_cases

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.utsav.mvvm_clean_architecture.data.remote.ApiResources
import com.utsav.mvvm_clean_architecture.data.remote.BaseDataSource
import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import com.utsav.mvvm_clean_architecture.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: AppRepository,
    private val baseDataSource: BaseDataSource
) {
    operator fun invoke(request: HashMap<String, String>): Flow<ApiResources<LoginDTO>> = flow {
        try {
            emit(ApiResources.loading())
            val loginResponse = loginRepository.login(request)
            val data = baseDataSource.getResult { loginResponse }
            emit(data)
        } catch (e: HttpException) {
            emit(ApiResources.error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(ApiResources.error("Couldn't reach server. Check your internet connection."))
        }
    }
}
