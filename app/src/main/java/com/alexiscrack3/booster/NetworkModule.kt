package com.alexiscrack3.booster

import com.alexiscrack3.booster.vocabulary.EntriesService
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { Gson() }
    single {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }
        val httpLoggingInterceptor = HttpLoggingInterceptor(logger)

        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    single {
        buildRetrofit(BuildConfig.BASE_URL, get(), get())
    }
//    factory {
//        val retrofit = get<Retrofit>()
//        retrofit.create(EntriesService::class.java)
//    }
}

private fun buildRetrofit(url: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        )
        .build()
}
