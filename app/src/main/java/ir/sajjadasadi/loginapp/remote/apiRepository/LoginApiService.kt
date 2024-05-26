package ir.sajjadasadi.loginapp.remote.apiRepository

import ir.sajjadasadi.loginapp.remote.dataModel.DefaultModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {
    @FormUrlEncoded
    @POST("email/login")
    suspend fun sendCode(
        @Field("email") email: String
    ): Response<DefaultModel>


    @FormUrlEncoded
    @POST("email/login/verify")
    suspend fun verifyCode(
        @Header("app-device-uid") androidid:String,
        @Field("code") code: String,
        @Field("email") email: String
    ): Response<DefaultModel>
}