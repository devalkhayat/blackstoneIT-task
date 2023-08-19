package com.blackstoneit.weatherforecasting.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tapadoo.alerter.Alerter

object Helper {
    /*fun loadImage(context: Context, path:String?, photo: ImageView?){
        Glide.with(context).load(path)
            .placeholder(com.blackstoneit.resources.R.color.app_gray) // placeholder
            .error(com.blackstoneit.resources.R.color.app_gray) // image broken
            .fallback(com.blackstoneit.resources.R.color.app_gray) // no image
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photo!!)
    }*/

    fun showAlert(context:Context,message:String){

        Alerter.create(context.getActivity()!!)
            .setTitle("Error")
            .setText(message)
            //.setBackgroundDrawable(context.resources.getDrawable(com.techcubics.resources.R.color.indian_red))
            .show()
    }
}