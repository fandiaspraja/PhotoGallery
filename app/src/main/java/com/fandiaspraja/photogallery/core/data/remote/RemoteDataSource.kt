package com.fandiaspraja.photogallery.core.data.remote

import android.util.Log
import com.fandiaspraja.photogallery.core.data.remote.network.ApiResponse
import com.fandiaspraja.photogallery.core.data.remote.network.ApiService
import com.fandiaspraja.photogallery.core.data.remote.response.PhotoResponseItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPhotos(clientId : String, page: Int, perPage: Int): Flow<ApiResponse<List<PhotoResponseItemResponse>>>{
        return channelFlow {
            try {
                val response = apiService.getPhotos(clientId, page, perPage)
                val data = response
                if (data != null){
                    if (data.size != 0){
                        send(ApiResponse.Success(response))
                    }else{
                        send(ApiResponse.Empty)
                    }
                }else{
                    send(ApiResponse.Empty)
                }
            } catch (e : Exception){
                e.printStackTrace()
                send(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPhotosById(id: String, clientId : String): Flow<ApiResponse<PhotoResponseItemResponse>>{
        return channelFlow {
            try {
                val response = apiService.getPhotosById(id, clientId)
                val data = response
                if (data != null){
                    send(ApiResponse.Success(response))
                }else{
                    send(ApiResponse.Empty)
                }
            } catch (e : Exception){
                e.printStackTrace()
                send(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchPhotos(clientId : String, page: Int, perPage: Int, query: String): Flow<ApiResponse<List<PhotoResponseItemResponse>>>{
        return channelFlow {
            try {
                val response = apiService.getSearchPhotos(clientId, page, perPage, query)
                val data = response.results
                if (data != null){
                    if (data.size != 0){
                        send(ApiResponse.Success(response.results))
                    }else{
                        send(ApiResponse.Empty)
                    }
                }else{
                    send(ApiResponse.Empty)
                }
            } catch (e : Exception){
                e.printStackTrace()
                send(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}