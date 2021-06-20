package com.suhaili.gameinfoapp.core.resource.remote

import android.util.Log
import com.suhaili.gameinfoapp.core.resource.remote.model.ResultsItem
import com.suhaili.gameinfoapp.core.util.vo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteServices @Inject constructor(private val apiconfig: APIServices) {


    fun getGamedata(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiconfig.GetAllGameData()
                val dataResponse = response.results
                if (dataResponse?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(dataResponse))
                } else {
                    emit(ApiResponse.Empty("Still Loading", dataResponse))
                }

            } catch (e: Exception) {
                Log.e("Error GET API", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}