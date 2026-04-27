package com.subot.core.data.mapper

import com.subot.core.data.dto.PenanggungJawabProfileDto
import com.subot.core.data.dto.PenanggungJawabResponseDto
import com.subot.core.domain.model.PenanggungJawab

fun PenanggungJawabProfileDto.toDomain(): PenanggungJawab = PenanggungJawab(
    id = id,
    role = role,
    idSekolah = idSekolah,
    sekolah = sekolah,
    alamat = alamat,
    namaPenanggungJawab = namaPenanggungJawab,
    emailPenanggungJawab = emailPenanggungJawab,
    telephonePenanggungJawab = telephonePenanggungJawab,
    status = status
)

fun PenanggungJawabResponseDto.toDomain(): PenanggungJawab = profile.toDomain()
