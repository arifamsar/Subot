package com.subot.core.domain.repository

import androidx.paging.PagingData
import com.subot.core.domain.model.Member
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.PenanggungJawab
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.model.UserProfileSummary
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): ApiResult<UserProfile>

    suspend fun getProfileMembers(
        page: Int = 1,
        perPage: Int = 10,
        search: String? = null
    ): ApiResult<PaginatedData<Member>>

    fun getProfileMembersPaged(
        perPage: Int = 10,
        search: String? = null
    ): Flow<PagingData<Member>>

    suspend fun updatePenanggungJawab(
        namaPenanggungJawab: String,
        emailPenanggungJawab: String,
        telephonePenanggungJawab: String
    ): ApiResult<PenanggungJawab>

    suspend fun updateMemberProfile(
        namaLengkap: String,
        tempatLahir: String?,
        tanggalLahir: String?,
        kelas: String?,
        alamat: String?,
        telephone: String?,
        namaOrtu: String?,
        workOrtu: String?,
        fotoProfile: ByteArray?,
        fotoProfileName: String?
    ): ApiResult<UserProfileSummary>

    suspend fun changePassword(
        currentPassword: String,
        password: String,
        passwordConfirmation: String
    ): ApiResult<String>
}

