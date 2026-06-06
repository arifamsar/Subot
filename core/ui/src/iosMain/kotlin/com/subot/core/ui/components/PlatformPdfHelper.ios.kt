package com.subot.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.*
import platform.UIKit.*
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

@Composable
actual fun rememberPlatformPdfHelper(): PlatformPdfHelper {
    return remember { PlatformPdfHelper() }
}

actual class PlatformPdfHelper {
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual fun saveAndOpenPdf(fileName: String, bytes: ByteArray) {
        val fileManager = NSFileManager.defaultManager
        val documentDirectories = fileManager.URLsForDirectory(
            NSDocumentDirectory,
            NSUserDomainMask
        )
        val documentDirectory = documentDirectories.firstOrNull() as? NSURL ?: return
        val fileUrl = documentDirectory.URLByAppendingPathComponent(fileName) ?: return
        val nsData = bytes.toNSData()
        val success = nsData.writeToURL(fileUrl, true)
        if (success) {
            dispatch_async(dispatch_get_main_queue()) {
                val keyWindow = UIApplication.sharedApplication.keyWindow
                val rootViewController = keyWindow?.rootViewController
                if (rootViewController != null) {
                    val interactionController = UIDocumentInteractionController.interactionControllerWithURL(fileUrl)
                    interactionController.delegate = object : NSObject(), UIDocumentInteractionControllerDelegateProtocol {
                        override fun documentInteractionControllerViewControllerForPreview(
                            controller: UIDocumentInteractionController
                        ): UIViewController {
                            return rootViewController
                        }
                    }
                    interactionController.presentPreviewAnimated(true)
                }
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun ByteArray.toNSData(): NSData = usePinned { pinned ->
        NSData.dataWithBytes(pinned.addressOf(0), size.toULong())
    }
}
