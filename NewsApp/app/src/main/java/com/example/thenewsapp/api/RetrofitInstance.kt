package com.example.thenewsapp.api

import com.example.thenewsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            // Logs HTTP responses and requests
            val logging = HttpLoggingInterceptor()

            //  Interceptor should log the full request and response bodies
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            // HTTP client Retrofit uses for network requests
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                // Configures Gson as the converter for JSON serialization
                // and deserialization of API requests and responses.
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}