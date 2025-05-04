package gr.sppzglou.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.data.BuildConfig
import gr.sppzglou.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        logger: HttpLoggingInterceptor,
        @ApplicationContext applicationContext: Context
    ): OkHttpClient {
        val time = 5L
        val type = TimeUnit.SECONDS
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(time, type)
            .connectTimeout(time, type)
            .writeTimeout(time, type)
            .readTimeout(time, type)
            .addInterceptor(logger)
            .addInterceptor(ConnectivityInterceptor(applicationContext))

        val okHttp = okHttpClient.build()

        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(logger)
        }
        return okHttp
    }

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder()
        .setLenient()
        .create()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}