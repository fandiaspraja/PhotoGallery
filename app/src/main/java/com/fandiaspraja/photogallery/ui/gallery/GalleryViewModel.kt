package com.fandiaspraja.photogallery.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.domain.model.Gallery
import com.fandiaspraja.photogallery.core.domain.usecase.GalleryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GalleryViewModel(private val galleryUseCase: GalleryUseCase): ViewModel() {

    private val _responsePhotos: MutableLiveData<Resource<List<Gallery>>> = MutableLiveData()
    val responsePhotos: LiveData<Resource<List<Gallery>>> = _responsePhotos

    fun getPhotos(clientId : String, page: Int, perPage: Int) = viewModelScope.launch(Dispatchers.IO) {
        galleryUseCase.getPhotos(clientId, page, perPage).collect {
            _responsePhotos.postValue(it)
        }
    }

    fun getSearchPhotos(clientId : String, page: Int, perPage: Int, query: String) = viewModelScope.launch(Dispatchers.IO) {
        galleryUseCase.getSearchPhotos(clientId, page, perPage, query).collect {
            _responsePhotos.postValue(it)
        }
    }

    private val _response: MutableLiveData<Resource<Gallery>> = MutableLiveData()
    val response: LiveData<Resource<Gallery>> = _response

    fun getPhotosById(id: String, clientId : String) = viewModelScope.launch(Dispatchers.IO) {
        galleryUseCase.getPhotosById(id, clientId).collect {
            _response.postValue(it)
        }
    }

}