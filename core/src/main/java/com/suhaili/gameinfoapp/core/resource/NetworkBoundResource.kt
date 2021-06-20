package com.suhaili.gameinfoapp.core.resource

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.suhaili.gameinfoapp.core.util.vo.ApiResponse
import com.suhaili.gameinfoapp.core.util.vo.Resource
import com.suhaili.gameinfoapp.core.util.vo.StatusResponse
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            val apiResponse = createCall().first()
            when (apiResponse.status) {
                StatusResponse.SUCCESS -> {
                    saveCallResult(apiResponse.body ?: return@flow)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.message))
                }

                StatusResponse.EMPTY -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    fun asFlow(): Flow<Resource<ResultType>> = result

    protected open fun onFetchFailed() {}

    @MainThread
    protected abstract fun loadFromDB(): Flow<ResultType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType): Boolean

    @MainThread
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    @WorkerThread
    protected abstract suspend fun saveCallResult(data: RequestType)


}