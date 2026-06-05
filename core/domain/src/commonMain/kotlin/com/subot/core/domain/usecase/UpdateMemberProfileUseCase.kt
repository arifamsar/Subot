package com.subot.core.domain.usecase

import com.subot.core.domain.model.UserProfileSummary
import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult

class UpdateMemberProfileUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        namaLengkap: String,
        tempatLahir: String?,
        tanggalLahir: String?,
        kelas: String?,
        alamat: String?,
        telephone: String?,
        namaOrtu: String?,
        workOrtu: String?,
        fotoProfile: ByteArray? = null,
        fotoProfileName: String? = null
    ): ApiResult<UserProfileSummary> {
        return profileRepository.updateMemberProfile(
            namaLengkap = namaLengkap,
            tempatLahir = tempatLahir,
            tanggalLahir = tanggalLahir,
            kelas = kelas,
            alamat = alamat,
            telephone = telephone,
            namaOrtu = namaOrtu,
            workOrtu = workOrtu,
            fotoProfile = fotoProfile,
            fotoProfileName = fotoProfileName
        )
    }
}
