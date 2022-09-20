package com.fandiaspraja.photogallery.core.data.repository

import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.data.remote.RemoteDataSource
import com.fandiaspraja.photogallery.core.data.remote.network.ApiResponse
import com.fandiaspraja.photogallery.core.domain.model.Gallery
import com.fandiaspraja.photogallery.core.domain.repository.IGalleryRepository
import com.fandiaspraja.photogallery.core.utils.AppExecutors
import com.fandiaspraja.photogallery.core.utils.DataMapper
import kotlinx.coroutines.flow.*


class GalleryRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): IGalleryRepository {


    override suspend fun getPhotos(
        clientId: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Gallery>>> {
        return flow {
            var dataPhoto = arrayListOf<Gallery>()

            try {

                when(val it = remoteDataSource.getPhotos(clientId, page, perPage).first()) {
                    is ApiResponse.Success -> {
                        dataPhoto.addAll(DataMapper.mapResponseGalleryDomain(it.data))
                        emit(Resource.Success(dataPhoto.toList()))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(dataPhoto.toList()))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage, dataPhoto.toList()))
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override suspend fun getPhotosById(id: String, clientId: String): Flow<Resource<Gallery>> {
        return flow {
            var dataPhoto = Gallery()

            try {

                when(val it = remoteDataSource.getPhotosById(id, clientId).first()) {
                    is ApiResponse.Success -> {
                        dataPhoto = DataMapper.mapResponseResponseGalleryToDomain(it.data)
                        emit(Resource.Success(dataPhoto))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(dataPhoto))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage, dataPhoto))
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override suspend fun getSearchPhotos(
        clientId: String,
        page: Int,
        perPage: Int,
        query: String
    ): Flow<Resource<List<Gallery>>> {
        return flow {
            var dataPhoto = arrayListOf<Gallery>()

            try {

                when(val it = remoteDataSource.getSearchPhotos(clientId, page, perPage, query).first()) {
                    is ApiResponse.Success -> {
                        dataPhoto.addAll(DataMapper.mapResponseGalleryDomain(it.data))
                        emit(Resource.Success(dataPhoto.toList()))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(dataPhoto.toList()))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage, dataPhoto.toList()))
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


}