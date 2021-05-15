package com.example.mobishop.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobishop.common.utils.Constants.FEATURE_ID_1
import com.example.mobishop.common.utils.Constants.FEATURE_ID_2
import com.example.mobishop.common.utils.Constants.FEATURE_ID_3
import com.example.mobishop.data.repository.MobiRepository
import com.example.mobishop.data.response.MobiShopResponse
import com.example.mobishop.domain.AppResult
import com.example.mobishop.domain.entities.MobiShopEntity
import com.example.mobishop.domain.entities.Option
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val mobiRepository: MobiRepository) : ViewModel() {

    private val _mobiResultLiveData: MutableLiveData<MobiShopEntity> = MutableLiveData()
    val mobiResultLiveData: LiveData<MobiShopEntity> get() = _mobiResultLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean> get() = _errorLiveData

    fun getMobiShopData() {
        viewModelScope.launch {
            when (val result = mobiRepository.getMobiData()) {
                is AppResult.Success -> {
                  _mobiResultLiveData.value = renderMobiShopViewEntity(result.data)
                }
                is AppResult.Failure -> {
                    _errorLiveData.value = true
                }
            }
        }
    }

    private val exclusionMap = mutableMapOf<String, MutableList<String>>()

    private suspend fun renderMobiShopViewEntity(mobiShopResponse: MobiShopResponse): MobiShopEntity {
       return withContext(Dispatchers.IO){
            val mobiShopEntity = MobiShopEntity()
            mobiShopResponse.exclusions.forEach { exclusions ->
                addElementToMap(getId(exclusions[0].featureId, exclusions[0].optionsId), getId(exclusions[1].featureId, exclusions[1].optionsId))
                addElementToMap(getId(exclusions[1].featureId, exclusions[1].optionsId), getId(exclusions[0].featureId, exclusions[0].optionsId))
            }
            mobiShopResponse.features.forEach { feature ->
                when(feature.featureId){
                    FEATURE_ID_1 -> {
                        mobiShopEntity.mobileList.addAll(feature.options.map { mapToOptions(it, feature.featureId) })
                    }

                    FEATURE_ID_2 -> {
                        mobiShopEntity.storageOptions.addAll(feature.options.map { mapToOptions(it, feature.featureId) })
                    }

                    FEATURE_ID_3 -> {
                        mobiShopEntity.otherFeatures.addAll(feature.options.map { mapToOptions(it, feature.featureId) })
                    }
                }
            }
            mobiShopEntity
        }
    }

    private fun addElementToMap(key: String, value: String) {
        if (key in exclusionMap.keys){
            exclusionMap[key]?.add(value)
        }
        else{
            exclusionMap[key] = mutableListOf(value)
        }
    }

    private fun mapToOptions(option: MobiShopResponse.Feature.Option, featureId: String): Option {
        option.apply {
            val id = getId(featureId, id)
            return Option(icon, id, name, exclusionMap[id])
        }
    }

    private fun getId(featureId: String, optionId: String) = "$featureId-$optionId"
}