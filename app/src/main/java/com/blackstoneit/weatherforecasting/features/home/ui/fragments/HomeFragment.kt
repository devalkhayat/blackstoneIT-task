package com.blackstoneit.weatherforecasting.features.home.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstoneit.domain.home.responses.WeatherResponse
import com.blackstoneit.weatherforecasting.R
import com.blackstoneit.weatherforecasting.databinding.FragmentHomeBinding
import com.blackstoneit.weatherforecasting.features.home.domain.models.SnapShotDetails
import com.blackstoneit.weatherforecasting.features.home.domain.responses.CityDirectionResponse
import com.blackstoneit.weatherforecasting.features.home.ui.adapters.DaysWeatherAdapter
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.HomeViewModel
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeIntents
import com.blackstoneit.weatherforecasting.features.home.ui.viewmodels.states.HomeViewStates
import com.blackstoneit.weatherforecasting.util.Constants.DEFAULT_CITY_LAT
import com.blackstoneit.weatherforecasting.util.Constants.DEFAULT_CITY_LNG
import com.blackstoneit.weatherforecasting.util.Helper
import com.blackstoneit.weatherforecasting.util.ShowComponent
import com.blackstoneit.weatherforecasting.util.eventListners.RecycleViewEventListener
import com.blackstoneit.weatherforecasting.util.models.FinalResponse
import com.blackstoneit.weatherforecasting.util.toNormaDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment() : Fragment() {
    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding?=null
    private val binding: FragmentHomeBinding get()=_binding!!
    private val homeViewModel:HomeViewModel by viewModels()
    private val shortList:ArrayList<SnapShotDetails> = ArrayList()
    private lateinit var onCityClickListener: RecycleViewEventListener
    private lateinit var progressShowHide:ShowComponent
    private lateinit var constainerShowHide: ShowComponent
    private  var selectedCity:String="Alexandria"

    @Inject
    lateinit var adapter: DaysWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.processIntent()
        listeners()
        events()
        render()
        return binding.root
    }
    //render
    private fun render() {

        lifecycleScope.launch {
            homeViewModel.state.collect {
                when (it) {
                    is HomeViewStates.ideal ->  start()
                    is HomeViewStates.showError -> showError(it.message)
                    is HomeViewStates.showWeatherInfo->showDetails(it.result)
                    is HomeViewStates.showCityLocation->getLocationInfo(it.result)
                    else->{}
                }

            }
        }
    }

    //
    private fun start() {
        lifecycleScope.launch {
            launch { progressShowHide.show() }
            launch { homeViewModel.intentChannel.send(HomeIntents.getWeatherInfo(DEFAULT_CITY_LAT,DEFAULT_CITY_LNG))  }
        }

    }
    private fun showError(msg:String){
        Helper.showAlert(requireContext(),msg)
        progressShowHide.hide()
        constainerShowHide.hide()
    }
    private fun showDetails(result: WeatherResponse) {
        progressShowHide.hide()
        //clear previous data
        clear()
        //adding week days
        result.list.forEach {
            //checking is the day is already saved on memory or not
            if(isNew(it.dt!!)){
                shortList.add(it)
            }
        }

        //header
        displayTodayDetails()
        //list
        displayWeekDays()

    }
    private fun getLocationInfo(result: ArrayList<CityDirectionResponse>) {

        lifecycleScope.launch {
            launch { progressShowHide.show() }
            launch {
                result[0]?.let {
                    homeViewModel.intentChannel.send(HomeIntents.getWeatherInfo(it.lat!!, it.lon!!))
                }
            }

        }
    }


    private fun isNew(date:Long):Boolean {
        var result = shortList.find { s -> s.dt?.toNormaDate().equals(date.toNormaDate()) }
        return result==null
    }
    private fun displayTodayDetails(){
        if(shortList.size>0) {
            var data=shortList.get(0)
            //
            binding.apply {
                tvCity.text = selectedCity
                data.main?.let {
                    tvDegree.text = String.format(getString(R.string.degree),it.temp.toString())
                    tvMaxMinDegree.text = String.format(getString(R.string.min_max_degree),it.tempMin.toString(),it.tempMax.toString())
                }
                data.weather[0]?.let {
                    tvStatus.text = it.main
                }

            }
        }
    }
    private fun displayWeekDays() {
        if (shortList.size > 0) {
            adapter.setItemsList(shortList)
            binding.apply {
                rvRestOfWeek.adapter = adapter
                rvRestOfWeek.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }
    private fun clear(){
        binding.apply {
            tvDegree.text=""
            tvMaxMinDegree.text=""
            rvRestOfWeek.adapter=null
            shortList.clear()
        }
    }
    private fun listeners(){
        progressShowHide=object:ShowComponent{
            override fun show() {
                binding.apply {
                    progress.visibility=View.VISIBLE
                    mainContainer.visibility=View.GONE
                }
            }

            override fun hide() {
                binding.apply {
                    progress.visibility=View.GONE
                    mainContainer.visibility=View.VISIBLE
                }
            }

        }
        constainerShowHide=object :ShowComponent{
            override fun show() {
                binding.apply {
                    mainContainer.visibility=View.VISIBLE
                }
            }

            override fun hide() {
                binding.apply {
                    mainContainer.visibility=View.GONE
                }
            }

        }

        onCityClickListener=object:RecycleViewEventListener{
            override fun onClick(name: String) {
                lifecycleScope.launch {
                    selectedCity=name
                    launch { progressShowHide.show() }
                    launch { homeViewModel.intentChannel.send(HomeIntents.getCityLocation(name)) }
                }
            }
        }
    }
    private fun events(){
        binding.tvCity.setOnClickListener {
            val action=HomeFragmentDirections.toCityChooserFragment(onCityClickListener)
            findNavController().navigate(action)
        }

    }
    //
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}


