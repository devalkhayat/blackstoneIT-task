package com.blackstoneit.domain.home.responses

import com.google.gson.annotations.SerializedName
import com.blackstoneit.weatherforecasting.features.home.domain.models.SnapShotDetails

data class WeatherResponse(
    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<SnapShotDetails> = arrayListOf(),
)