package com.subot.profile.screens.change_password

class ChangePasswordValidator {
    fun validateCurrentPassword(password: String): String? {
        if (password.isBlank()) {
            return "Kata sandi saat ini wajib diisi"
        }
        return null
    }

    fun validateNewPassword(password: String): String? {
        if (password.isBlank()) {
            return "Kata sandi baru wajib diisi"
        }
        if (password.length < 8) {
            return "Kata sandi harus minimal 8 karakter"
        }
        return null
    }

    fun validateConfirmPassword(password: String, newPassword: String): String? {
        if (password.isBlank()) {
            return "Konfirmasi kata sandi baru wajib diisi"
        }
        if (password != newPassword) {
            return "Konfirmasi kata sandi tidak cocok"
        }
        return null
    }
}
