package com.blackstoneit.weatherforecasting.features.home.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstoneit.weatherforecasting.R
import com.blackstoneit.weatherforecasting.databinding.FragmentCityChooserBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.City
import com.blackstoneit.weatherforecasting.features.home.ui.adapters.CitiesAdapter
import com.blackstoneit.weatherforecasting.features.home.ui.adapters.DaysWeatherAdapter
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.HomeViewModel
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeIntents
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeViewStates
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CityChooserFragment : BottomSheetDialogFragment() {

    private val TAG = "CityChooserFragment"
    private var _binding: FragmentCityChooserBinding?=null
    private val binding: FragmentCityChooserBinding get()=_binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private val args: CityChooserFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCityChooserBinding.inflate(inflater, container, false)
        homeViewModel.processIntent()
        events()
        render()
        return binding.root
    }
    //render
    private fun render() {
        lifecycleScope.launch {
            homeViewModel.state.collect {
                when (it) {
                    is HomeViewStates.ideal ->callCities()
                    is HomeViewStates.showCities->showData(it.result)
                    else->{}
                }
            }
        }
    }
    private fun events() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
    private fun callCities(){
        lifecycleScope.launch {
            homeViewModel.intentChannel.send(HomeIntents.getCities)
        }
    }
    private fun showData(items: ArrayList<City>?) {
        if(items?.size!!>0){
            adapter.setInital(items,this,args.onItemClickListener)
            binding.apply {
                rvResult.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                rvResult.adapter=adapter
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}