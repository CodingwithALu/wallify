package com.example.wallify.feature.wallify.home.model

data class Topics(
    val id: String,
    val slug: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val updatedAt: String,
    val startsAt: String,
    val endsAt: String?,
    val onlySubmissionsAfter: String?,
    val visibility: String,
    val featured: Boolean,
    val totalPhotos: Int,
    val currentUserContributions: List<Any>,
    val totalCurrentUserSubmissions: Int?,
    val links: Links,
    val mediaType: List<String>,
    val status: String,
    val owners: List<Owner>,
    val coverPhoto: CoverPhoto,
    val previewPhotos: List<PreviewPhoto>
)

data class Links(
    val self: String,
    val html: String,
    val photos: String
)

data class Owner(
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: OwnerLinks,
    val profileImage: ProfileImage,
    val instagramUsername: String?,
    val totalCollections: Int?,
    val totalLikes: Int?,
    val totalPhotos: Int?,
    val totalPromotedPhotos: Int?,
    val totalIllustrations: Int?,
    val totalPromotedIllustrations: Int?,
    val acceptedTos: Boolean?,
    val forHire: Boolean?,
    val social: Social?
)

data class OwnerLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String
)

data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

data class Social(
    val instagramUsername: String?,
    val portfolioUrl: String?,
    val twitterUsername: String?,
    val paypalEmail: String?
)

data class CoverPhoto(
    val id: String,
    val slug: String,
    val alternativeSlugs: AlternativeSlugs,
    val createdAt: String,
    val updatedAt: String,
    val promotedAt: String?,
    val width: Int,
    val height: Int,
    val color: String?,
    val blurHash: String?,
    val description: String?,
    val altDescription: String?,
    val breadcrumbs: List<Any>,
    val urls: Urls,
    val links: PhotoLinks,
    val likes: Int?,
    val likedByUser: Boolean?,
    val bookmarked: Boolean?,
    val currentUserCollections: List<Any>?,
    val sponsorship: Any?,
    val topicSubmissions: Map<String, TopicSubmission>?,
    val assetType: String?,
    val user: User?
)

data class AlternativeSlugs(
    val en: String?,
    val es: String?,
    val ja: String?,
    val fr: String?,
    val it: String?,
    val ko: String?,
    val de: String?,
    val pt: String?,
    val id: String?
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val smallS3: String?
)

data class PhotoLinks(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)

data class TopicSubmission(
    val status: String?,
    val approvedOn: String?
)

data class User(
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String?,
    val lastName: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: OwnerLinks?,
    val profileImage: ProfileImage?,
    val instagramUsername: String?,
    val totalCollections: Int?,
    val totalLikes: Int?,
    val totalPhotos: Int?,
    val totalPromotedPhotos: Int?,
    val totalIllustrations: Int?,
    val totalPromotedIllustrations: Int?,
    val acceptedTos: Boolean?,
    val forHire: Boolean?,
    val social: Social?
)

data class PreviewPhoto(
    val id: String,
    val slug: String,
    val createdAt: String,
    val updatedAt: String,
    val blurHash: String?,
    val assetType: String?,
    val urls: Urls
)

