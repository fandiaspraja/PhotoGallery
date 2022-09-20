package com.fandiaspraja.photogallery.core.data.remote.response

data class PhotoResponseItemResponse(
	val topic_submissions: TopicSubmissions? = null,
	val current_user_collections: List<Any>? = null,
	val color: String? = null,
	val sponsorship: Sponsorship? = null,
	val created_at: String? = null,
	val description: String? = null,
	val liked_by_user: Boolean? = null,
	val urls: Urls? = null,
	val alt_description: Any? = null,
	val updated_at: String? = null,
	val width: Int? = null,
	val blur_hash: String? = null,
	val links: Links? = null,
	val id: String? = null,
	val categories: List<Any>? = null,
	val promoted_at: Any? = null,
	val user: User? = null,
	val height: Int? = null,
	val likes: Int? = null
)
