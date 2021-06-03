package com.easeplantz.easeplantz.core.di

import androidx.room.Room
import com.easeplantz.easeplantz.core.data.EaseplantzRepository
import com.easeplantz.easeplantz.core.data.source.local.LocalDataSource
import com.easeplantz.easeplantz.core.data.source.local.room.EaseplantzDatabase
import com.easeplantz.easeplantz.core.data.source.remote.RemoteDataSource
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiServices
import com.easeplantz.easeplantz.core.domain.repository.IEaseplantzRepository
import com.easeplantz.easeplantz.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<EaseplantzDatabase>().easeplantzDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EaseplantzDatabase::class.java, "easeplantz.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://support.bemcandra.ga:5050/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiServices::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IEaseplantzRepository> { EaseplantzRepository(get(), get(), get()) }
}