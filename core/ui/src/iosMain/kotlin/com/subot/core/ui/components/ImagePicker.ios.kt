package com.subot.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerSourceTypePhotoLibrary
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.Foundation.NSData
import platform.Foundation.getBytes
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.UIKit.UIApplication
import platform.darwin.NSObject

@Composable
actual fun rememberImagePicker(onImagePicked: (ByteArray, String) -> Unit): ImagePickerLauncher {
    val delegate = remember {
        object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
            @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
            override fun imagePickerController(
                picker: UIImagePickerController,
                didFinishPickingMediaWithInfo: Map<Any?, *>
            ) {
                val image = didFinishPickingMediaWithInfo[platform.UIKit.UIImagePickerControllerEditedImage] as? UIImage
                    ?: didFinishPickingMediaWithInfo[platform.UIKit.UIImagePickerControllerOriginalImage] as? UIImage
                
                if (image != null) {
                    val data = UIImageJPEGRepresentation(image, 0.8)
                    if (data != null) {
                        val bytes = data.toByteArray()
                        onImagePicked(bytes, "profile.jpg")
                    }
                }
                picker.dismissViewControllerAnimated(true, null)
            }

            override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                picker.dismissViewControllerAnimated(true, null)
            }
        }
    }

    return remember {
        ImagePickerLauncher {
            val picker = UIImagePickerController().apply {
                sourceType = UIImagePickerControllerSourceTypePhotoLibrary
                this.delegate = delegate
            }
            val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            rootViewController?.presentViewController(picker, animated = true, completion = null)
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray {
    val length = this.length.toInt()
    val bytes = ByteArray(length)
    if (length > 0) {
        bytes.usePinned { pinned ->
            platform.Foundation.memcpy(pinned.addressOf(0), this.bytes, this.length)
        }
    }
    return bytes
}

actual class ImagePickerLauncher(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}
