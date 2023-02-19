package com.nour.ieeeevent.util


import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.BindingAdapter
import com.nour.ieeeevent.R
import com.nour.ieeeevent.util.Constants.Image_STATE_NOT_FOUND
import com.nour.ieeeevent.util.Constants.Image_STATE_NOT_VIB
import com.nour.ieeeevent.util.Constants.Image_STATE_VIB


object BindingAdapter {

    @BindingAdapter("changeOutEllipseBackground")
    @JvmStatic
    fun changeOutEllipseBackground(imageView: View, imageState: Int?) {
            imageView.background =
                when (imageState) {
                  Image_STATE_VIB-> getDrawable(imageView.context, R.drawable.ellipse_1_vib)
                  Image_STATE_NOT_VIB -> getDrawable(imageView.context, R.drawable.ellipse_1)
                  Image_STATE_NOT_FOUND-> getDrawable(imageView.context, R.drawable.ellipse_1_not_found)
                    else -> getDrawable(imageView.context, R.drawable.empty)
               }
    }


    @BindingAdapter("changeMidEllipseBackground")
    @JvmStatic
    fun changeMidEllipseBackground(imageView: View, imageState: Int?) {
            imageView.background =
              when (imageState) {
                 Image_STATE_VIB-> getDrawable(imageView.context, R.drawable.ellipse_2_vib)
                 Image_STATE_NOT_VIB -> getDrawable(imageView.context, R.drawable.ellipse_2)
                 Image_STATE_NOT_FOUND-> getDrawable(imageView.context, R.drawable.ellipse_2_not_found)
                  else -> getDrawable(imageView.context, R.drawable.empty)
              }
    }

    @BindingAdapter("changeInternalEllipseBackground")
    @JvmStatic
    fun changeInternalEllipseBackground(imageView: ImageView, imageState: Int?) {
            imageView.background =
                when (imageState) {
                    Image_STATE_VIB-> getDrawable(imageView.context, R.drawable.ellipse_3_vib)
                    Image_STATE_NOT_VIB -> getDrawable(imageView.context, R.drawable.ellipse_3)
                    Image_STATE_NOT_FOUND-> getDrawable(imageView.context, R.drawable.ellipse_3_not_found)
                    else -> getDrawable(imageView.context, R.drawable.empty)
                }
    }
}