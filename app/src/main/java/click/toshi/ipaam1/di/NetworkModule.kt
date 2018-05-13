package click.toshi.ipaam1.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import click.toshi.ipaam1.data.api.ApplicationApi
import click.toshi.ipaam1.data.api.response.mapper.ApplicationJsonAdapterFactory
import click.toshi.ipaam1.data.api.response.mapper.InstantAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.threeten.bp.Instant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
        includes = [BuildTypeBasedNetworkModule::class]
)
open class NetworkModule {

    companion object {
        val instance = NetworkModule()
    }

    @Singleton @Provides
    fun provideOkHttpClient(@NetworkLogger loggingInterceptors: Set<@JvmSuppressWildcards
    Interceptor>):
            OkHttpClient =
            OkHttpClient.Builder().apply {
                loggingInterceptors.forEach {
                    addNetworkInterceptor(it)
                }
            }.build()

    @RetrofitApplication @Singleton @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://panic0.toshi.click/ipaam1/")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .add(Instant::class.java, InstantAdapter())
                        .build()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    @RetrofitGoogleForm @Singleton @Provides
    fun provideRetrofitForGoogleForm(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://docs.google.com/forms/d/")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .add(Instant::class.java, InstantAdapter())
                        .build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @RetrofitGithub @Singleton @Provides
    fun provideRetrofitForGithub(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .add(Instant::class.java, InstantAdapter())
                        .build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(okHttpClient)
                .build()
    }

    @Singleton @Provides
    open fun provideApplicationApi(@RetrofitApplication retrofit: Retrofit): ApplicationApi {
        return retrofit.create(ApplicationApi::class.java)
    }
}
