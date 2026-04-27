package com.subot.core.domain.model

data class PenanggungJawab(
    val id: Int,
    val role: String,
    val idSekolah: String,
    val sekolah: String,
    val alamat: String,
    val namaPenanggungJawab: String,
    val emailPenanggungJawab: String,
    val telephonePenanggungJawab: String,
    val status: String
)
