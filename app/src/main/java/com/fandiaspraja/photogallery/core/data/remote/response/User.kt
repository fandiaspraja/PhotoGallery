package com.fandiaspraja.photogallery.core.data.remote.response

data class User(
	val total_photos: Int? = null,
	val accepted_tos: Boolean? = null,
	val social: Social? = null,
	val twitter_username: String? = null,
	val last_name: Any? = null,
	val bio: String? = null,
	val total_likes: Int? = null,
	val portfolio_url: String? = null,
	val profile_image: ProfileImage? = null,
	val updated_at: String? = null,
	val for_hire: Boolean? = null,
	val name: String? = null,
	val location: Any? = null,
	val links: Links? = null,
	val total_collections: Int? = null,
	val id: String? = null,
	val first_name: String? = null,
	val instagram_username: String? = null,
	val username: String? = null
)
