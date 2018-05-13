package click.toshi.ipaam1.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
internal class BuildTypeBasedNetworkModule {

    @NetworkLogger @Singleton @Provides @IntoSet
    fun provideStetho(): Interceptor = StethoInterceptor()

    @NetworkLogger @Singleton @Provides @IntoSet
    fun provideNetworkLogger(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
