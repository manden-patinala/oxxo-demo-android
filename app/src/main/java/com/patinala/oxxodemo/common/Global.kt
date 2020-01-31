package com.patinala.oxxodemo.common

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.util.DisplayMetrics
import android.view.WindowManager
import java.math.BigInteger
import java.security.MessageDigest


class Global {

    companion object {

        fun getCurrentTimeMillis(): String {
            return System.currentTimeMillis().toString()
        }

        fun getHash(): String {
            return "${getCurrentTimeMillis()}${Constants.API_PRIVATE_KEY}${Constants.API_KEY}".md5()
        }

        fun getWidthScreen(context: Context): Int {
            val windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
        }

    }

}