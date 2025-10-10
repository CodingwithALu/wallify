package com.example.wallify.feature.wallify.home.model

data class Photos(
    val id: String,
    val slug: String,
    val alternativeSlugs: AlternativeSlugs,
    val createdAt: String,
    val updatedAt: String,
    val promotedAt: String?,
    val width: Int,
    val height: Int,
    val color: String?,
    val blur_hash: String?,
    val description: String?,
    val altDescription: String?,
    val breadcrumbs: List<Any>,
    val urls: Urls,
    val links: PhotoLinks,
    val likes: Int,
    val likedByUser: Boolean,
    val bookmarked: Boolean,
    val currentUserCollections: List<Any>,
    val sponsorship: Any?,
    val topicSubmissions: Map<String, TopicSubmission>?,
    val assetType: String?,
    val user: User?,
    val exif: Exif?,
    val location: Location?,
    val meta: Meta?,
    val publicDomain: Boolean,
    val tags: List<Tag> = emptyList(),
    val views: Int = 0,
    val downloads: Int = 0,
    val topics: List<Topic> = emptyList()
)

data class Topic(
    val id: String,
    val title: String,
    val slug: String,
    val visibility: String
)
data class Tag(
    val type: String?,
    val title: String?
)
data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?
)

data class Location(
    val name: String?,
    val city: String?,
    val country: String?,
    val position: Position?
)

data class Position(
    val latitude: Double?,
    val longitude: Double?
)

data class Meta(
    val index: Boolean?
)
