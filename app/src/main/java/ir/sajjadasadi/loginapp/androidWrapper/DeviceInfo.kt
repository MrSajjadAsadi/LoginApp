package ir.sajjadasadi.loginapp.androidWrapper

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object DeviceInfo {
    var androidid: String? = null

    @SuppressLint("HardwareIds")
    fun getAndroidUID(context: Context): String {
        if (androidid == null) {
            androidid = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }
        return androidid ?: ""
    }
}