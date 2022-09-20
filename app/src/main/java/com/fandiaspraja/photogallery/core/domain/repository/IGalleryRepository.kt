package com.fandiaspraja.photogallery.core.domain.repository

import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.domain.model.Gallery
import kotlinx.coroutines.flow.Flow

interface IGalleryRepository {

    suspend fun getPhotos(clientId : String, page: Int, perPage: Int): Flow<Resource<List<Gallery>>>

    suspend fun getPhotosById(id: String, clientId : String): Flow<Resource<Gallery>>

    suspend fun getSearchPhotos(clientId : String, page: Int, perPage: Int, query: String): Flow<Resource<List<Gallery>>>
}