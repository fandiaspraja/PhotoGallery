package com.fandiaspraja.photogallery.core.data.remote.network

import com.fandiaspraja.photogallery.core.data.remote.response.PhotoResponse
import com.fandiaspraja.photogallery.core.data.remote.response.PhotoResponseItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    abstact class for get data from api

    @GET("photos")
    suspend fun getPhotos(@Query("client_id") clientId : String, @Query("page") page: Int, @Query("per_page") perPage: Int): List<PhotoResponseItemResponse>

    @GET("photos/{id}")
    suspend fun getPhotosById(@Path("id") id: String, @Query("client_id") clientId : String): PhotoResponseItemResponse

    @GET("search/photos")
    suspend fun getSearchPhotos(@Query("client_id") clientId : String, @Query("page") page: Int, @Query("per_page") perPage: Int, @Query("query") query: String): PhotoResponse
}