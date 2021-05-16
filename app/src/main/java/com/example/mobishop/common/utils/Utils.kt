package com.example.mobishop.common.utils

object Utils {
    const val BASE_URL = "https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db"
    const val ERROR_CODE = "E01"
    const val FEATURE_ID_MOBILE_PHONE = "1"
    const val FEATURE_ID_STORAGE = "2"
    const val FEATURE_ID_OTHER_FEATURE = "3"

    private const val OPTION_ID_STORAGE_1 = "6"
    private const val OPTION_ID_STORAGE_2 = "7"

    private const val OPTION_ID_OTHER_1 = "10"
    private const val OPTION_ID_OTHER_2 = "11"
    private const val OPTION_ID_OTHER_3 = "12"

    fun getId(featureId: String, optionId: String) = "$featureId-$optionId"

    fun getRb1Id() = getId(FEATURE_ID_STORAGE, OPTION_ID_STORAGE_1)
    fun getRb2Id() = getId(FEATURE_ID_STORAGE, OPTION_ID_STORAGE_2)
    fun getCb1Id() = getId(FEATURE_ID_OTHER_FEATURE, OPTION_ID_OTHER_1)
    fun getCb2Id() = getId(FEATURE_ID_OTHER_FEATURE, OPTION_ID_OTHER_2)
    fun getCb3Id() = getId(FEATURE_ID_OTHER_FEATURE, OPTION_ID_OTHER_3)





}