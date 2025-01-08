package com.example.rahul.convert

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.InputStream

open class Convert {
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToByteArray(imageString: String): ByteArray {
        // Decode the base64 string to a byte array

        return   java.util.Base64.getDecoder().decode(imageString)
    }

    fun uriToByteArray(context: Context?, imageUri: Uri): ByteArray? {
        val inputStream: InputStream? = context?.contentResolver?.openInputStream(imageUri)
        val byteArrayOutputStream = ByteArrayOutputStream()

        inputStream?.use { input ->
            val buffer = ByteArray(1024)
            var length: Int
            while (input.read(buffer).also { length = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, length)
            }
        }

        return byteArrayOutputStream.toByteArray()
    }


}




