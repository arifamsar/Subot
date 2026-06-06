package com.subot.core.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

@Composable
actual fun rememberPlatformPdfHelper(): PlatformPdfHelper {
    val context = LocalContext.current
    return remember(context) { PlatformPdfHelper(context) }
}

actual class PlatformPdfHelper(private val context: Context) {
    actual fun saveAndOpenPdf(fileName: String, bytes: ByteArray) {
        try {
            val cacheFolder = File(context.cacheDir, "reports")
            if (!cacheFolder.exists()) {
                cacheFolder.mkdirs()
            }
            val file = File(cacheFolder, fileName)
            FileOutputStream(file).use { fos ->
                fos.write(bytes)
                fos.flush()
            }
            val authority = "${context.packageName}.fileprovider"
            val uri: Uri = FileProvider.getUriForFile(context, authority, file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
