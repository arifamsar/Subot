package com.subot.profile.screens.penanggung_jawab

class PenanggungJawabValidator {
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Nama tidak boleh kosong"
            name.length < 3 -> "Nama minimal 3 karakter"
            else -> null
        }
    }

    fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email tidak boleh kosong"
            !isValidEmail(email) -> "Format email tidak valid"
            else -> null
        }
    }

    fun validatePhone(phone: String): String? {
        return when {
            phone.isBlank() -> "Nomor telepon tidak boleh kosong"
            !phone.matches(Regex("^\\d{10,15}$")) -> "Nomor telepon harus 10-15 digit"
            else -> null
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$"))
    }

    fun validate(name: String, email: String, phone: String): Boolean {
        return validateName(name) == null && 
               validateEmail(email) == null && 
               validatePhone(phone) == null
    }
}
