package ir.sajjadasadi.loginapp.remote

import ir.sajjadasadi.loginapp.remote.apiRepository.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitService {
    private const val BaseUrl = "https://learn.alirezaahmadi.info/api/v1/auth/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

     val apiService: LoginApiService = retrofit.create(LoginApiService::class.java)

}