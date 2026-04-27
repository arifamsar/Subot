package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PenanggungJawabRequestDto(
    @SerialName("nama_penanggung_jawab")
    val namaPenanggungJawab: String,

    @SerialName("email_penanggung_jawab")
    val emailPenanggungJawab: String,

    @SerialName("telephone_penanggung_jawab")
    val telephonePenanggungJawab: String
)

@Serializable
data class PenanggungJawabProfileDto(
    @SerialName("id")
    val id: Int,

    @SerialName("role")
    val role: String,

    @SerialName("id_sekolah")
    val idSekolah: String,

    @SerialName("sekolah")
    val sekolah: String,

    @SerialName("alamat")
    val alamat: String,

    @SerialName("nama_penanggung_jawab")
    val namaPenanggungJawab: String,

    @SerialName("email_penanggung_jawab")
    val emailPenanggungJawab: String,

    @SerialName("telephone_penanggung_jawab")
    val telephonePenanggungJawab: String,

    @SerialName("status")
    val status: String
)

@Serializable
data class PenanggungJawabResponseDto(
    @SerialName("profile")
    val profile: PenanggungJawabProfileDto
)
