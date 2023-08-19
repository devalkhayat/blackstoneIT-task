package com.blackstoneit.weatherforecasting.features.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneit.weatherforecasting.databinding.ItemCityBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.ui.adapters.holders.CityHolderItem
import com.blackstoneit.weatherforecasting.features.home.ui.fragments.CityChooserFragment
import com.blackstoneit.weatherforecasting.util.eventListners.RecycleViewEventListener
import javax.inject.Inject

class CitiesAdapter @Inject constructor(): RecyclerView.Adapter<CityHolderItem>()  {

    private lateinit var _items:MutableList<City>
    private lateinit var _itemBinding: ItemCityBinding
    private lateinit var _holderFragment: CityChooserFragment
    private lateinit var _onClickListener: RecycleViewEventListener

    fun setInital(items:ArrayList<City>,holderFragment: CityChooserFragment,onClickListener: RecycleViewEventListener) {

        _items = items
        _holderFragment=holderFragment
        _onClickListener=onClickListener

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolderItem {
        _itemBinding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityHolderItem(_itemBinding,_onClickListener,_holderFragment)
    }
    override fun onBindViewHolder(holder: CityHolderItem, position: Int) {
        holder.bind(_items.get(position))
    }
    override fun getItemCount(): Int {
        return _items.size
    }
}