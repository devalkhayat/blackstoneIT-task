package com.blackstoneit.weatherforecasting.features.home.ui.adapters.holders

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneit.weatherforecasting.R
import com.blackstoneit.weatherforecasting.databinding.ItemDayBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.SnapShotDetails
import com.blackstoneit.weatherforecasting.util.toNormaDate
import com.blackstoneit.weatherforecasting.util.toWeekDay
import javax.inject.Inject

class DayHolderItem (private val _context: Context,
                                        private val binding: ItemDayBinding,
                                        ): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: SnapShotDetails){
        binding.apply {
            tvMaxMinDegree.text = String.format(
                _context.getString(R.string.min_max_degree),
                data.main?.tempMin.toString(),
                data.main?.tempMax.toString()
            )
            tvDayName.text = data.dtTxt?.toString()?.toWeekDay()
        }

    }
}