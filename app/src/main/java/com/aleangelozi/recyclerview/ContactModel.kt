package com.aleangelozi.recyclerview

data class ContactModel(
    val name: String,
    val age: Int
) {
    val imageUrl = "https://picsum.photos/150?random=$age"
    val landScapeImageUrl = "https://picsum.photos/300/150?random=$age"
}
