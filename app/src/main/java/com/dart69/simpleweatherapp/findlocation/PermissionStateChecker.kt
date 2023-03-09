package com.dart69.simpleweatherapp.findlocation

import android.content.Context
import android.content.pm.PackageManager

interface PermissionStateChecker {
    fun isGranted(permission: String): Boolean

    class Default(
        private val context: Context
    ) : PermissionStateChecker {
        override fun isGranted(permission: String): Boolean =
            context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    }
}