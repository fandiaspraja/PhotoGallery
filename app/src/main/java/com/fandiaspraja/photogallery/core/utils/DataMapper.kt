package com.fandiaspraja.photogallery.core.utils

import com.fandiaspraja.photogallery.core.data.remote.response.PhotoResponseItemResponse
import com.fandiaspraja.photogallery.core.domain.model.Gallery


object DataMapper {

//    class for change response to data object

    fun mapResponseGalleryDomain(it: List<PhotoResponseItemResponse>): List<Gallery>{

        var listGallery = ArrayList<Gallery>()

        it?.map {
            var gallery = Gallery(
                it?.id,
                it?.urls?.regular,
                it?.description,
                it?.user?.name,
                it?.user?.bio
            )
            listGallery.add(gallery)

        }

        return listGallery
    }

    fun mapResponseResponseGalleryToDomain(it: PhotoResponseItemResponse): Gallery{

        var gallery = Gallery(
            it?.id,
            it?.urls?.regular,
            it?.description,
            it?.user?.name,
            it?.user?.bio
        )

        return gallery
    }

}