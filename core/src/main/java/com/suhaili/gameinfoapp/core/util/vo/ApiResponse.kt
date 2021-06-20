package com.suhaili.gameinfoapp.core.util.vo

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {

    companion object {
        fun <T> Success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)
        fun <T> Empty(message: String, body: T): ApiResponse<T> =
            ApiResponse(StatusResponse.EMPTY, body, message)

        fun <T> Error(message: String? = null): ApiResponse<T> =
            ApiResponse(StatusResponse.ERROR, null, message)
    }

}