package com.subot.core.domain.model

data class Member(
    val id: Int,
    val role: String,
    val namaLengkap: String,
    val nis: String,
    val kelas: String,
    val idSekolah: String,
    val statusSiswa: String,
    val profileImageUrl: String? = null
)
