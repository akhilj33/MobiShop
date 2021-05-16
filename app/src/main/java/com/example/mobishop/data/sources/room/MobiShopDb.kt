package com.example.mobishop.data.sources.room

import com.example.mobishop.common.utils.Utils
import com.example.mobishop.data.response.MobiShopResponse
import com.example.mobishop.domain.AppError
import com.example.mobishop.domain.AppResult

interface MobiShopDb {
    suspend fun getMobiShopResponse(): AppResult<MobiShopResponse>
    suspend fun saveMobiShopResponse(mobiShopItem: MobiShopResponse)
}

class MobiShopDbImpl(private val dao: MobiShopDao) : MobiShopDb {
    override suspend fun getMobiShopResponse(): AppResult<MobiShopResponse> {
        val features = dao.getFeatures()?.map { mapToMobiShopFeatures(it) }
        val exclusions = dao.getExclusions()?.map { mapToMobiShopExclusions(it) }
        return if (features != null && exclusions != null)
            AppResult.Success(MobiShopResponse(exclusions, features))
        else
            AppResult.Failure(AppError(Utils.ERROR_CODE))
    }

    override suspend fun saveMobiShopResponse(mobiShopItem: MobiShopResponse) {
        val features = mobiShopItem.features
        val exclusions = mobiShopItem.exclusions
        dao.deleteExclusions()
        dao.deleteFeatures()
        dao.saveFeatures(features.map { mapToMobiRoomFeatures(it) })
        dao.saveExclusions(exclusions.map { mapToMobiRoomExclusions(it) })
    }
}

private fun mapToMobiRoomExclusions(exclusions: List<MobiShopResponse.Exclusion>): MobiRoomExclusions {
    return MobiRoomExclusions(
        featureId1 = exclusions[0].featureId, optionId1 = exclusions[0].optionsId,
        featureId2 = exclusions[1].featureId, optionId2 = exclusions[1].optionsId
    )
}

private fun mapToMobiRoomFeatures(feature: MobiShopResponse.Feature): MobiRoomFeatures {
    feature.apply {
        return MobiRoomFeatures(featureId, name, options.map { Option(it.icon, it.id, it.name) })
    }
}

private fun mapToMobiShopFeatures(mobiRoomFeatures: MobiRoomFeatures): MobiShopResponse.Feature {
    mobiRoomFeatures.apply {
        return MobiShopResponse.Feature(
            featureId,
            name,
            options.map { MobiShopResponse.Feature.Option(it.icon, it.id, it.name) })
    }
}

private fun mapToMobiShopExclusions(mobiRoomExclusions: MobiRoomExclusions): List<MobiShopResponse.Exclusion> {
    val exclusionList = mutableListOf<MobiShopResponse.Exclusion>()
    exclusionList.add(
        MobiShopResponse.Exclusion(
            featureId = mobiRoomExclusions.featureId1,
            optionsId = mobiRoomExclusions.optionId1
        )
    )

    exclusionList.add(
        MobiShopResponse.Exclusion(
            featureId = mobiRoomExclusions.featureId2,
            optionsId = mobiRoomExclusions.optionId2
        )
    )
    return exclusionList
}
