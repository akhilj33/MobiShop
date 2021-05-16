package com.example.mobishop.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobishop.common.utils.Utils.FEATURE_ID_MOBILE_PHONE
import com.example.mobishop.common.utils.Utils.FEATURE_ID_STORAGE
import com.example.mobishop.common.utils.Utils.FEATURE_ID_OTHER_FEATURE
import com.example.mobishop.common.utils.Utils.getId
import com.example.mobishop.data.repository.MobiRepository
import com.example.mobishop.data.response.MobiShopResponse
import com.example.mobishop.domain.AppResult
import com.example.mobishop.domain.entities.MobiShopEntity
import com.example.mobishop.domain.entities.Option
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val mobiRepository: MobiRepository) : ViewModel() {

    private val _mobiResultLiveData: MutableLiveData<MutableList<MobiShopEntity>> = MutableLiveData()
    val mobiResultLiveData: LiveData<MutableList<MobiShopEntity>> get() = _mobiResultLiveData

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

    private suspend fun renderMobiShopViewEntity(mobiShopResponse: MobiShopResponse): MutableList<MobiShopEntity> {
       return withContext(Dispatchers.IO){
            val mobiShopEntityList = mutableListOf<MobiShopEntity>()
            mobiShopResponse.exclusions.forEach { exclusions ->
                addElementToMap(getId(exclusions[0].featureId, exclusions[0].optionsId), getId(exclusions[1].featureId, exclusions[1].optionsId))
                addElementToMap(getId(exclusions[1].featureId, exclusions[1].optionsId), getId(exclusions[0].featureId, exclusions[0].optionsId))
            }

           val storageList = mobiShopResponse.features[1].options.map { mapToOptions(it, FEATURE_ID_STORAGE) }
           val otherFeaturesList = mobiShopResponse.features[2].options.map { mapToOptions(it, FEATURE_ID_OTHER_FEATURE) }

           mobiShopResponse.features[0].options.forEach {
               mobiShopEntityList.add(MobiShopEntity(mapToOptions(it, FEATURE_ID_MOBILE_PHONE), storageList, otherFeaturesList))
           }
            mobiShopEntityList
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
            return Option(icon, id, name, exclusionMap[id]?: mutableListOf())
        }
    }
}