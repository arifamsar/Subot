package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("login_as")
    val loginAs: String,

    @SerialName("identifier")
    val identifier: String,

    @SerialName("password")
    val password: String,

    @SerialName("device_name")
    val deviceName: String
)

@Serializable
data class UserProfileSummaryDto(
    @SerialName("id")
    val id: Int,

    @SerialName("nis")
    val nis: String? = null,

    @SerialName("role")
    val role: String? = null,

    @SerialName("id_sekolah")
    val idSekolah: String? = null,

    @SerialName("sekolah")
    val sekolah: String? = null,

    @SerialName("alamat")
    val alamat: String? = null,

    @SerialName("nama_lengkap")
    val namaLengkap: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,

    @SerialName("nama_penanggung_jawab")
    val namaPenanggungJawab: String? = null,

    @SerialName("email_penanggung_jawab")
    val emailPenanggungJawab: String? = null,

    @SerialName("telephone_penanggung_jawab")
    val telephonePenanggungJawab: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("tempat_lahir")
    val tempatLahir: String? = null,

    @SerialName("tanggal_lahir")
    val tanggalLahir: String? = null,

    @SerialName("kelas")
    val kelas: String? = null,

    @SerialName("telephone")
    val telephone: String? = null,

    @SerialName("nama_ortu")
    val namaOrtu: String? = null,

    @SerialName("work_ortu")
    val workOrtu: String? = null,

    @SerialName("status_siswa")
    val statusSiswa: String? = null
)

@Serializable
data class AuthUserDto(
    @SerialName("type")
    val type: String,

    @SerialName("profile")
    val profile: UserProfileSummaryDto
)

@Serializable
data class AuthTokenDto(
    @SerialName("token_type")
    val tokenType: String,

    @SerialName("access_token")
    val accessToken: String,

    @SerialName("user_type")
    val userType: String,

    @SerialName("user")
    val user: AuthUserDto
)

@Serializable
data class UserProfileDto(
    @SerialName("type")
    val type: String,

    @SerialName("profile")
    val profile: UserProfileSummaryDto
)

@Serializable
data class MemberProfileDataDto(
    @SerialName("profile")
    val profile: UserProfileSummaryDto
)
