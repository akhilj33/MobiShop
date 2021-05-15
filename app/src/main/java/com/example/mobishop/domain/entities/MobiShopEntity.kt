package com.example.mobishop.domain.entities

data class MobiShopEntity(
        val mobileList: MutableList<Option> = mutableListOf(),
        val storageOptions: MutableList<Option> = mutableListOf(),
        val otherFeatures: MutableList<Option> = mutableListOf()
)

data class Option(
        val icon: String,
        val id: String,
        val name: String,
        val exclusions: MutableList<String>?
)
