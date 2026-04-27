package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberDto(
    @SerialName("id")
    val id: Int,

    @SerialName("role")
    val role: String,

    @SerialName("nama_lengkap")
    val namaLengkap: String,

    @SerialName("nis")
    val nis: String,

    @SerialName("kelas")
    val kelas: String? = null,

    @SerialName("id_sekolah")
    val idSekolah: String,

    @SerialName("status_siswa")
    val statusSiswa: String,

    @SerialName("profile_image_url")
    val profileImageUrl: String? = null
)

@Serializable
data class PaginatedMembersDto(
    @SerialName("items")
    val items: List<MemberDto>,

    @SerialName("pagination")
    val pagination: PaginationDto
)
