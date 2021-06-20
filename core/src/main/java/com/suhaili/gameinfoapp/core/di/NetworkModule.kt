package com.suhaili.gameinfoapp.core.di

import com.suhaili.gameinfoapp.core.BuildConfig
import com.suhaili.gameinfoapp.core.resource.remote.APIServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun certificateKey(): CertificatePinner =
        CertificatePinner.Builder()
            .add(BuildConfig.HOSTNAME, BuildConfig.KEY_CERTIFICATE)
            .build()

    @Provides
    fun makeClient(key: CertificatePinner): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(key)
            .build()
    }

    @Provides
    fun makeServicesRetro(client: OkHttpClient): APIServices {
        val retro = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retro.create(APIServices::class.java)
    }


}