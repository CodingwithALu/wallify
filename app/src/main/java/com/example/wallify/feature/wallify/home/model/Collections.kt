package com.example.wallify.feature.wallify.home.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collections(
    val id: String,
    val title: String,
    val description: String?,
    @SerializedName("published_at") val publishedAt: String?,
    @SerializedName("last_collected_at") val lastCollectedAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val featured: Boolean?,
    @SerializedName("total_photos") val totalPhotos: Int,
    @SerializedName("private") val isPrivate: Boolean?,
    @SerializedName("share_key") val shareKey: String?,
    val links: CollectionLinks?,
    val user: CollectionUser,
    @SerializedName("cover_photo") val coverPhoto: CollectionCoverPhoto?,
    @SerializedName("preview_photos") val previewPhotos: List<CollectionPreviewPhoto>
) : Serializable {
    companion object {
        fun empty(): Collections {
            return Collections(
                id = "",
                title = "",
                description = null,
                publishedAt = null,
                lastCollectedAt = null,
                updatedAt = null,
                featured = false,
                totalPhotos = 0,
                isPrivate = false,
                shareKey = null,
                links = null,
                user = CollectionUser(
                    id = null,
                    updatedAt = null,
                    username = null,
                    name = null,
                    firstName = null,
                    lastName = null,
                    twitterUsername = null,
                    portfolioUrl = null,
                    bio = null,
                    location = null,
                    links = null,
                    profileImage = null,
                    instagramUsername = null,
                    totalCollections = null,
                    totalLikes = null,
                    totalPhotos = null,
                    totalPromotedPhotos = null,
                    totalIllustrations = null,
                    acceptedTos = null,
                    forHire = null,
                    social = null
                ),
                coverPhoto = null,
                previewPhotos = emptyList()
            )
        }
    }
}

data class CollectionLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val related: String?
) : Serializable

data class CollectionUser(
    val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val username: String?,
    val name: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("twitter_username") val twitterUsername: String?,
    @SerializedName("portfolio_url") val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: UserLinks?,
    @SerializedName("profile_image") val profileImage: ProfileImage?,
    @SerializedName("instagram_username") val instagramUsername: String?,
    @SerializedName("total_collections") val totalCollections: Int?,
    @SerializedName("total_likes") val totalLikes: Int?,
    @SerializedName("total_photos") val totalPhotos: Int?,
    @SerializedName("total_promoted_photos") val totalPromotedPhotos: Int?,
    @SerializedName("total_illustrations") val totalIllustrations: Int?,
    @SerializedName("accepted_tos") val acceptedTos: Boolean?,
    @SerializedName("for_hire") val forHire: Boolean?,
    val social: Social?
) : Serializable

data class UserLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?
) : Serializable

data class CollectionProfileImage(
    val small: String?,
    val medium: String?,
    val large: String?
) : Serializable

data class CollectionSocial(
    @SerializedName("instagram_username") val instagramUsername: String?,
    @SerializedName("portfolio_url") val portfolioUrl: String?,
    @SerializedName("twitter_username") val twitterUsername: String?,
    @SerializedName("paypal_email") val paypalEmail: String?
) : Serializable

data class CollectionCoverPhoto(
    val id: String?,
    val slug: String?,
    @SerializedName("alternative_slugs") val alternativeSlugs: Map<String, String>?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val promoted_at: String?,
    val width: Int?,
    val height: Int?,
    val color: String?,
    @SerializedName("blur_hash") val blurHash: String?,
    val description: String?,
    @SerializedName("alt_description") val altDescription: String?,
    val breadcrumbs: List<Breadcrumb>?,
    val urls: Urls?,
    val links: CollectionPhotoLinks?,
    val likes: Int?,
    @SerializedName("liked_by_user") val likedByUser: Boolean?,
    val bookmarked: Boolean?,
    @SerializedName("current_user_collections") val currentUserCollections: List<Any>?,
    val sponsorship: Any?,
    @SerializedName("topic_submissions") val topicSubmissions: Map<String, Any>?,
    @SerializedName("asset_type") val assetType: String?,
    val user: CoverPhotoUser?
) : Serializable

data class Breadcrumb(val title: String?) : Serializable

data class CollectionUrls(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?,
    @SerializedName("small_s3") val smallS3: String?
) : Serializable

data class CollectionPhotoLinks(
    val self: String?,
    val html: String?,
    val download: String?,
    @SerializedName("download_location") val downloadLocation: String?
) : Serializable

data class CoverPhotoUser(
    val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val username: String?,
    val name: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    val twitter_username: String?,
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val links: UserLinks?,
    @SerializedName("profile_image") val profileImage: CollectionProfileImage?,
    @SerializedName("instagram_username") val instagramUsername: String?,
    @SerializedName("total_collections") val totalCollections: Int?,
    @SerializedName("total_likes") val totalLikes: Int?,
    @SerializedName("total_photos") val totalPhotos: Int?,
    @SerializedName("total_promoted_photos") val totalPromotedPhotos: Int?,
    @SerializedName("accepted_tos") val acceptedTos: Boolean?,
    @SerializedName("for_hire") val forHire: Boolean?,
    val social: CollectionSocial?
) : Serializable

data class CollectionPreviewPhoto(
    val id: String?,
    val slug: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("blur_hash") val blurHash: String?,
    @SerializedName("asset_type") val assetType: String?,
    val urls: CollectionUrls
) : Serializable
