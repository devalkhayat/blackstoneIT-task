package com.blackstoneit.weatherforecasting.features.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneit.weatherforecasting.databinding.ItemDayBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.SnapShotDetails
import com.blackstoneit.weatherforecasting.features.home.ui.adapters.holders.DayHolderItem
import javax.inject.Inject

class DaysWeatherAdapter @Inject constructor(): RecyclerView.Adapter<DayHolderItem>() {


    lateinit var items:MutableList<SnapShotDetails>
    lateinit var itemBinding: ItemDayBinding

    fun setItemsList(_items:MutableList<SnapShotDetails>) {

        items = _items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolderItem {
        itemBinding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayHolderItem(parent.context,itemBinding)
    }

    override fun onBindViewHolder(holder: DayHolderItem, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}