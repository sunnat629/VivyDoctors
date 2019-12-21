package dev.sunnat629.vivydoctors.ui.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dev.sunnat629.vivydoctors.ui.di.Authorized
import dev.sunnat629.vivydoctors.ui.di.GsonUtcDateAdapter
import dev.sunnat629.vivydoctors.ui.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ApiModule::class, AppModule::class])
abstract class RemoteModule {

    @Module
    companion object {

        private const val HEADER_AUTHORIZATION = "Authorization"

        @JvmStatic
        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(DateTime::class.java, GsonUtcDateAdapter())
                .create()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideInterceptor(): Interceptor {
            return Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        @JvmStatic
        @Singleton
        @Provides
        @Authorized
        fun provideAuthorizedInterceptor(
        ): Interceptor {
            return Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val accessToken = String() // todo add auth with raw or from sharedpref
                val tokenType = String() // todo add auth with raw or from sharedpref

                requestBuilder.header(HEADER_AUTHORIZATION, "$tokenType $accessToken")

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            interceptor: Interceptor
        ): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor(httpLoggingInterceptor)
            return httpClient.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        @Authorized
        fun provideAuthorizedOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            @Authorized interceptor: Interceptor
        ): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
            return httpClient.build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(
            httpClient: OkHttpClient,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .client(httpClient)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        @Authorized
        fun provideAuthorizedRetrofit(
            @Authorized httpClient: OkHttpClient,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .client(httpClient)
                .build()
        }
    }
}