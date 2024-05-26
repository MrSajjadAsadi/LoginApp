package ir.sajjadasadi.loginapp.remote.ext

import com.google.gson.Gson
import ir.sajjadasadi.loginapp.remote.dataModel.ErrorModel
import retrofit2.Response

object ErrorUtils {

    fun getError(response: Response<*>): String {

        var error: String? = null
        val errorBody = response.errorBody()
        if (errorBody != null) {
            error = Gson().fromJson(errorBody.string(), ErrorModel::class.java).message
        }
        return error ?:"ارتباط با سرور امکان پذیر نبود"

    }

}