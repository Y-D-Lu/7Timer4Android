package cn.arsenals.seventimer.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.READ_CONTACTS
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import java.util.*


object PermissionUtil {

    private const val TAG = "PermissionUtil"

    val PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
    )

    fun checkPermission(ctx: Context, args: Array<String>): Array<String> {
        val lackedPermissions = mutableSetOf<String>()
        for (arg in args) {
            if (ContextCompat.checkSelfPermission(ctx, arg) != PackageManager.PERMISSION_GRANTED) {
                lackedPermissions.add(arg)
            }
        }
        LogUtil.d(TAG, "lackedPermissions : ${Arrays.toString(lackedPermissions.toTypedArray())}")
        return lackedPermissions.toTypedArray()
    }
}
