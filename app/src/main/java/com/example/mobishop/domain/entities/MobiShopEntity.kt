package com.example.mobishop.domain.entities

data class MobiShopEntity(
    val mobileItem: Option,
    val storageOptions: List<Option>,
    val otherFeatures: List<Option>
)

data class Option(
        val icon: String,
        val id: String,
        val name: String,
        val exclusions: MutableList<String>
)
