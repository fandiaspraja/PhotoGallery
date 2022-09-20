package com.fandiaspraja.photogallery.core.data.remote.response

data class PhotoResponse(
	val total: Int? = null,
	val total_pages: Int? = null,
	val results: List<PhotoResponseItemResponse>? = null
)
