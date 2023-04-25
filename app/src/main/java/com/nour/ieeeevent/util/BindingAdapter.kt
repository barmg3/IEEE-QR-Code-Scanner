package com.nour.ieeeevent.util

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter


@BindingAdapter("progressState")
fun progressState(progressBar: ProgressBar, visible :Boolean){
    if(visible) progressBar.visibility = View.VISIBLE
    else progressBar.visibility = View.GONE
}