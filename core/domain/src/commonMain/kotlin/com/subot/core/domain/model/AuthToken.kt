package com.subot.core.domain.model

/**
 * Represents the authenticated user's token and type after a successful login.
 */
data class AuthToken(
    val tokenType: String,
    val accessToken: String,
    val userType: String,
    val user: AuthUser
)

data class AuthUser(
    val type: String,
    val profile: UserProfileSummary
)

data class UserProfileSummary(
    val id: Int,
    val nis: String?,
    val role: String? = null,
    val idSekolah: String? = null,
    val sekolah: String? = null,
    val alamat: String? = null,
    val namaLengkap: String? = null,
    val email: String? = null,
    val profileImageUrl: String? = null,
    val namaPenanggungJawab: String? = null,
    val emailPenanggungJawab: String? = null,
    val telephonePenanggungJawab: String? = null,
    val status: String? = null
)
