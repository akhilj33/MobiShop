package com.example.mobishop.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobishop.common.utils.Utils.getCb1Id
import com.example.mobishop.common.utils.Utils.getCb2Id
import com.example.mobishop.common.utils.Utils.getCb3Id
import com.example.mobishop.common.utils.Utils.getRb1Id
import com.example.mobishop.common.utils.Utils.getRb2Id
import com.example.mobishop.databinding.MobiitemLayoutBinding
import com.example.mobishop.domain.entities.MobiShopEntity

class MobiShopAdapter(val onSubmitClick: (MobiShopSelectedItem?) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private val items: MutableList<MobiShopEntity> = mutableListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MobiItemViewHolder(MobiitemLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as MobiItemViewHolder).bind(item)
    }

    fun addItems(mobiItemList: List<MobiShopEntity>) {
        val newIndex = items.size
        val newItemsCount = mobiItemList.size
        if (items.addAll(mobiItemList)) notifyItemRangeInserted(newIndex, newItemsCount)
    }

    inner class MobiItemViewHolder(private val viewBinding: MobiitemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(mobiEntity: MobiShopEntity) {
            val mobileItem = mobiEntity.mobileItem
            val storageOptions = mobiEntity.storageOptions
            val otherFeatures = mobiEntity.otherFeatures

            viewBinding.apply {
                tvMobileName.text = mobileItem.name
                rb1.text = storageOptions[0].name
                rb2.text = storageOptions[1].name
                cb1.text = otherFeatures[0].name
                cb2.text = otherFeatures[1].name
                cb3.text = otherFeatures[2].name
            }

            mobileItem.exclusions.forEach { disableViews(it) }

            // Listening checked and unchecked state of radiobutton and check box. According to it
            // enabling and disabling views as per exclusions list
            viewBinding.rb1.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) storageOptions[0].exclusions.forEach { disableViews(it) }
                else storageOptions[0].exclusions.filter { !mobileItem.exclusions.contains(it) }
                    .forEach { enableViews(it) }
            }

            viewBinding.rb2.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) storageOptions[1].exclusions.forEach { disableViews(it) }
                else storageOptions[1].exclusions.filter { !mobileItem.exclusions.contains(it) }
                    .forEach { enableViews(it) }
            }

            viewBinding.cb1.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) otherFeatures[0].exclusions.forEach { disableViews(it) }
                else otherFeatures[0].exclusions.filter { !mobileItem.exclusions.contains(it) }
                    .forEach { enableViews(it) }
            }

            viewBinding.cb2.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) otherFeatures[1].exclusions.forEach { disableViews(it) }
                else otherFeatures[1].exclusions.filter { !mobileItem.exclusions.contains(it) }
                    .forEach { enableViews(it) }
            }

            viewBinding.cb3.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) otherFeatures[2].exclusions.forEach { disableViews(it) }
                else otherFeatures[2].exclusions.filter { !mobileItem.exclusions.contains(it) }
                    .forEach { enableViews(it) }
            }


            // On Button click fetches all users response and send it to lambda. Also if user has
            // not selected all required combination that shows a toast telling him/her to do so.
            viewBinding.apply {
                btnSubmit.setOnClickListener {
                    var storage = ""
                    val features = mutableListOf<String>()
                    if (rb1.isChecked)
                        storage = storageOptions[0].name
                    else if (rb2.isChecked)
                        storage = storageOptions[1].name

                    if (cb1.isChecked)
                        features.add(otherFeatures[0].name)
                    if (cb2.isChecked)
                        features.add(otherFeatures[1].name)
                    if (cb3.isChecked)
                        features.add(otherFeatures[2].name)

                    if ((rb1.isChecked || rb2.isChecked) && (cb1.isChecked || cb2.isChecked || cb3.isChecked)) {
                        onSubmitClick(MobiShopSelectedItem(mobileItem.name, storage, features))
                    } else onSubmitClick(null)
                }

            }
        }

        private fun disableViews(id: String) {
            when (id) {
                getRb1Id() -> viewBinding.rb1.isEnabled = false
                getRb2Id() -> viewBinding.rb2.isEnabled = false
                getCb1Id() -> viewBinding.cb1.isEnabled = false
                getCb2Id() -> viewBinding.cb2.isEnabled = false
                getCb3Id() -> viewBinding.cb3.isEnabled = false
            }
        }

        private fun enableViews(id: String) {
            when (id) {
                getRb1Id() -> viewBinding.rb1.isEnabled = true
                getRb2Id() -> viewBinding.rb2.isEnabled = true
                getCb1Id() -> viewBinding.cb1.isEnabled = true
                getCb2Id() -> viewBinding.cb2.isEnabled = true
                getCb3Id() -> viewBinding.cb3.isEnabled = true
            }
        }
    }


}