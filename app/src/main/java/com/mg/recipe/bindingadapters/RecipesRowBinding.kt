package com.mg.recipe.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.mg.recipe.R

class RecipesRowBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("loadImageFromUrl")
        fun loadImageFromUrl(view: ImageView, url: String) {
            view.load(url) {
                crossfade(600)
                error(R.drawable.ic_error_red)
            }
        }

        @JvmStatic
        @BindingAdapter("setNumberOfLikes")
        fun setNumberOfLikes(view: TextView, likes: Int) {
            view.text = likes.toString()
        }

        @JvmStatic
        @BindingAdapter("setNumberOfMinutes")
        fun setNumberOfMinutes(view: TextView, minutes: Int) {
            view.text = minutes.toString()
        }

        @JvmStatic
        @BindingAdapter("applyVeganColor")
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                val greenColor = ContextCompat.getColor(view.context, R.color.green)
                when (view) {
                    is TextView -> view.setTextColor(greenColor)
                    is ImageView -> view.setColorFilter(greenColor)
                }
            }
        }
    }
}