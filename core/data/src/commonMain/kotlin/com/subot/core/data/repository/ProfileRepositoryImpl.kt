package com.subot.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.subot.core.data.dto.ChangePasswordRequestDto
import com.subot.core.data.dto.MemberProfileDataDto
import com.subot.core.data.dto.PaginatedMembersDto
import com.subot.core.data.dto.PenanggungJawabRequestDto
import com.subot.core.data.dto.UserProfileDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.paging.MembersPagingSource
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.data.util.safeApiCallNoData
import com.subot.core.domain.model.Member
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.PenanggungJawab
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.model.UserProfileSummary
import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : ProfileRepository {
    override suspend fun getProfile(): ApiResult<UserProfile> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<UserProfileDto> {
            apiService.getProfile(token = token)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun getProfileMembers(
        page: Int,
        perPage: Int,
        search: String?
    ): ApiResult<PaginatedData<Member>> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<PaginatedMembersDto> {
            apiService.getProfileMembers(token = token, page = page, perPage = perPage, search = search)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override fun getProfileMembersPaged(
        perPage: Int,
        search: String?
    ): Flow<PagingData<Member>> {
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false,
                prefetchDistance = perPage
            ),
            pagingSourceFactory = { MembersPagingSource(apiService, userPreferences, search, perPage) }
        ).flow
    }

    override suspend fun updatePenanggungJawab(
        namaPenanggungJawab: String,
        emailPenanggungJawab: String,
        telephonePenanggungJawab: String
    ): ApiResult<PenanggungJawab> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val request = PenanggungJawabRequestDto(
            namaPenanggungJawab = namaPenanggungJawab,
            emailPenanggungJawab = emailPenanggungJawab,
            telephonePenanggungJawab = telephonePenanggungJawab
        )
        val result = safeApiCall<com.subot.core.data.dto.PenanggungJawabResponseDto> {
            apiService.updatePenanggungJawab(token = token, request = request)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun updateMemberProfile(
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
    ): ApiResult<UserProfileSummary> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<MemberProfileDataDto> {
            apiService.updateMemberProfile(
                token = token,
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
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.profile.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun changePassword(
        currentPassword: String,
        password: String,
        passwordConfirmation: String
    ): ApiResult<String> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val request = ChangePasswordRequestDto(
            currentPassword = currentPassword,
            password = password,
            passwordConfirmation = passwordConfirmation
        )
        return safeApiCallNoData {
            apiService.changePassword(token = token, request = request)
        }
    }
}

