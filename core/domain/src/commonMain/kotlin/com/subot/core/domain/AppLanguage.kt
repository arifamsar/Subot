package com.subot.core.domain

enum class AppLanguage(val code: String) {
    ENGLISH("en"),
    INDONESIAN("id");

    companion object {
        fun getByCode(code: String): AppLanguage {
            return entries.firstOrNull { it.code == code } ?: ENGLISH
        }
    }
}