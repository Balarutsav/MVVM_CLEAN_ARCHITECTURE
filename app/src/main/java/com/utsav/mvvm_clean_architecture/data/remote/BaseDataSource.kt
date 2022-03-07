package com.utsav.mvvm_clean_architecture.data.remote
import com.utsav.mvvm_clean_architecture.data.remote.dto.BaseDTO
import retrofit2.Response


open class BaseDataSource {

    open suspend fun <T> getResult(call: suspend () -> Response<T>): ApiResources<T> {
        try {
            val response = call()
            if (response.code() == 403) {
                return ApiResources.error("", code = response.code())
            } else if (response.body() != null) {
                val baseResponse = (response.body() as BaseDTO)
                if (response.isSuccessful && baseResponse.status) {
                    response.body()?.let {
                        return ApiResources.success(it, baseResponse.message)
                    }
                } else {
                    val body = response.body()
                    if (body != null) {
                        var msg = baseResponse.message.trim()
                        if (msg.isEmpty()) {
                            msg = "Internal server error"
                        }
                        return ApiResources.error(msg)
                    }
                }
                return error("Success => ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return error("Error => ${e.message} ?: $e")
        }
        return ApiResources.error("")
    }

    private fun <T> error(message: String): ApiResources<T> {
        return ApiResources.error(message)
    }
}