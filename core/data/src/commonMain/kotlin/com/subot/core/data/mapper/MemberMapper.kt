package com.subot.core.data.mapper

import com.subot.core.data.dto.MemberDto
import com.subot.core.data.dto.PaginatedMembersDto
import com.subot.core.domain.model.Member
import com.subot.core.domain.model.PaginatedData

import com.subot.core.data.util.formatImageUrl

fun MemberDto.toDomain(): Member = Member(
    id = id,
    role = role,
    namaLengkap = namaLengkap,
    nis = nis,
    kelas = kelas.orEmpty(),
    idSekolah = idSekolah,
    statusSiswa = statusSiswa,
    profileImageUrl = formatImageUrl(profileImageUrl)
)

fun PaginatedMembersDto.toDomain(): PaginatedData<Member> = PaginatedData(
    items = items.map { it.toDomain() },
    pagination = pagination.toDomain()
)
