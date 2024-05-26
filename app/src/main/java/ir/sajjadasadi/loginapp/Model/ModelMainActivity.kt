package ir.sajjadasadi.loginapp.Model

import android.content.Context
import ir.sajjadasadi.loginapp.androidWrapper.DeviceInfo

class ModelMainActivity(private val context: Context) {
    fun getID() = DeviceInfo.getAndroidUID(context)
}