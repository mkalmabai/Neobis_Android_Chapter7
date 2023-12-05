package com.example.lorby.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.lorby.R
import com.google.android.material.snackbar.Snackbar

class showSnackbar {

    companion object {
        fun showCustomSnackbar(
            context: Context,
            rootView: View,
            message: String
        ) {
            val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
            val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            snackbar.view.layoutParams = params
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                .setTextColor(ContextCompat.getColor(context, R.color.red))
            snackbar.view.background = ContextCompat.getDrawable(context, R.drawable.snackbar)
            snackbar.show()
        }
    }

}