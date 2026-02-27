package com.subot.core.data.mapper

import com.subot.core.data.dto.AuthTokenDto
import com.subot.core.data.dto.AuthUserDto
import com.subot.core.data.dto.UserProfileDto
import com.subot.core.data.dto.UserProfileSummaryDto
import com.subot.core.domain.model.AuthToken
import com.subot.core.domain.model.AuthUser
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.model.UserProfileSummary

fun UserProfileSummaryDto.toDomain(): UserProfileSummary = UserProfileSummary(
    id = id,
    nis = nis
)

fun AuthUserDto.toDomain(): AuthUser = AuthUser(
    type = type,
    profile = profile.toDomain()
)

fun AuthTokenDto.toDomain(): AuthToken = AuthToken(
    tokenType = tokenType,
    accessToken = accessToken,
    userType = userType,
    user = user.toDomain()
)

fun UserProfileDto.toDomain(): UserProfile = UserProfile(
    type = type,
    profile = profile.toDomain()
)

