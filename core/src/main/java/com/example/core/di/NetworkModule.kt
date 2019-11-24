package com.example.core.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
class NetworkModule {

    companion object {

        private const val API_ENDPOINT = "https://api.myjson.com/"
    }

    private val stethoInterceptor = StethoInterceptor()
    private val loggingInterceptor = HttpLoggingInterceptor(
        object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i("request: %s ", message)
            }
        }
    )
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @PerApp
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addNetworkInterceptor(stethoInterceptor)
            .build()
    }

    @Provides
    @PerApp
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(API_ENDPOINT)
            .build()
    }
}