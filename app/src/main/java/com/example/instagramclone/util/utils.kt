package com.example.instagramclone.util

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback:(String?)->Unit): String {
    var imageUrl=String()
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener{
            it.storage.downloadUrl.addOnSuccessListener {its->
                imageUrl = its.toString()
                callback(imageUrl)
            }
        }
    return imageUrl
}