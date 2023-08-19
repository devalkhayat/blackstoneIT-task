package com.blackstoneit.weatherforecasting.features.home.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.blackstoneit.weatherforecasting.databinding.ItemCityBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.ui.fragments.CityChooserFragment
import com.blackstoneit.weatherforecasting.util.eventListners.RecycleViewEventListener

class CityHolderItem(
    private val binding: ItemCityBinding,
    private val clickEventListener: RecycleViewEventListener,
    private val holderFragment: CityChooserFragment
):RecyclerView.ViewHolder(binding.root) {

    fun bind(data: City){
        binding.apply {
            tvTitle.text=data.name
            root.setOnClickListener {
                clickEventListener.onClick(data.name)
                holderFragment.dismiss()
            }
        }

    }

}