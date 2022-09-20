package com.fandiaspraja.photogallery.core.domain.usecase

import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.domain.model.Gallery
import com.fandiaspraja.photogallery.core.domain.repository.IGalleryRepository
import kotlinx.coroutines.flow.Flow

class GalleryInteractor(private val iGalleryRepository: IGalleryRepository): GalleryUseCase {

    override suspend fun getPhotos(
        clientId: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Gallery>>> =
        iGalleryRepository.getPhotos(clientId, page, perPage)

    override suspend fun getPhotosById(id: String, clientId: String): Flow<Resource<Gallery>> =
        iGalleryRepository.getPhotosById(id, clientId)

    override suspend fun getSearchPhotos(
        clientId: String,
        page: Int,
        perPage: Int,
        query: String
    ): Flow<Resource<List<Gallery>>> = iGalleryRepository.getSearchPhotos(clientId, page, perPage, query)
}