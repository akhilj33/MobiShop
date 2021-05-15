package com.example.mobishop.data.response


import com.google.gson.annotations.SerializedName

data class MobiShopResponse(
    @SerializedName("exclusions")
    val exclusions: List<List<Exclusion>>,
    @SerializedName("features")
    val features: List<Feature>
) {
    data class Exclusion(
        @SerializedName("feature_id")
        val featureId: String,
        @SerializedName("options_id")
        val optionsId: String
    )

    data class Feature(
        @SerializedName("feature_id")
        val featureId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("options")
        val options: List<Option>
    ) {
        data class Option(
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String
        )
    }
}