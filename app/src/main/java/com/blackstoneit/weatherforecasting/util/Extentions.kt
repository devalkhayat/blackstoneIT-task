package com.blackstoneit.weatherforecasting.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.toNormaDate():String{
    val normalDate: Date = Date(this*1000)
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    return format.format(normalDate)
}

fun String.toDate():Date{
    val d=SimpleDateFormat("dd-MM-yyyy").parse(this)
    return d
}
fun String.toWeekDay():String{
    val format = SimpleDateFormat("EEEE",Locale.US)
    return format.format(this.toDate())
}

fun Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> this.baseContext.getActivity()
        is ContextThemeWrapper -> this.baseContext.getActivity()
        else -> null
    }
}