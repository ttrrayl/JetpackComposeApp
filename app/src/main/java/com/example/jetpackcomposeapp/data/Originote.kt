package com.example.jetpackcomposeapp.data

data class Originote(
    val id: Long,
    val name: String,
    val photoUrl: Int,
    val price: String,
    val skinType: String,
    val desc: String
)

data class OriginoteList(
    val originote: Originote,
    val count: Int
)
