package com.subot.profile.screens.edit_profile

class EditProfileValidator {
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Nama tidak boleh kosong"
            name.length < 3 -> "Nama minimal 3 karakter"
            else -> null
        }
    }

    fun validatePhone(phone: String): String? {
        if (phone.isBlank()) return null
        return when {
            !phone.matches(Regex("^\\d{10,15}$")) -> "Nomor telepon harus 10-15 digit"
            else -> null
        }
    }

    fun validateDate(date: String): String? {
        if (date.isBlank()) return null
        return when {
            !date.matches(Regex("^\\d{4}-\\d{2}-\\d{2}$")) -> "Format tanggal harus YYYY-MM-DD"
            else -> null
        }
    }
}
