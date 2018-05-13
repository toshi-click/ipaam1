package click.toshi.ipaam1.test.di

import dagger.Provides
import click.toshi.ipaam1.data.api.ApplicationApi
import click.toshi.ipaam1.di.NetworkModule
import click.toshi.ipaam1.test.data.api.StubApplicationApi
import retrofit2.Retrofit

class StubNetworkModule : NetworkModule() {

    @Provides
    override fun provideApplicationApi(retrofit: Retrofit): ApplicationApi
        = StubApplicationApi()

}
