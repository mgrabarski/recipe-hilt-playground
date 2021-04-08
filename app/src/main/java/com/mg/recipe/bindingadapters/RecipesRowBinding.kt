package com.mg.recipe.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class RecipesRowBinding {

    companion object {

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
    }
}