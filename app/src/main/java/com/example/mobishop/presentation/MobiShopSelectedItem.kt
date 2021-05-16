package com.example.mobishop.presentation

data class MobiShopSelectedItem(
    val name: String="",
    val storage: String="",
    val otherFeatures: MutableList<String> = mutableListOf()
)
