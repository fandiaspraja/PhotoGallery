package com.fandiaspraja.photogallery.core.data.remote.response

data class Sponsorship(
	val sponsor: Sponsor? = null,
	val tagline_url: String? = null,
	val tagline: String? = null,
	val impression_urls: List<String>? = null
)
