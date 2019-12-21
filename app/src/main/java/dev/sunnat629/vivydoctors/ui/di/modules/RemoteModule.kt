package dev.sunnat629.vivydoctors.ui.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dev.sunnat629.vivydoctors.BuildConfig
import dev.sunnat629.vivydoctors.ui.di.GsonUTCDateAdapter
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.BASE_URL
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.CONNECT_TIMEOUT
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.READ_TIMEOUT
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.WRITE_TIMEOUT
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
abstract class RemoteModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(DateTime::class.java, GsonUTCDateAdapter())
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
        @Provides
        @Singleton
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            interceptor: Interceptor
        ): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(httpLoggingInterceptor)
            }
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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .build()
        }
    }
}