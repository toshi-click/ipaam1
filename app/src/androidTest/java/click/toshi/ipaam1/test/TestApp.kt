package click.toshi.ipaam1.test

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import click.toshi.ipaam1.di.DaggerAppComponent
import click.toshi.ipaam1.presentation.App
import click.toshi.ipaam1.test.di.StubDatabaseModule
import click.toshi.ipaam1.test.di.StubNetworkModule

class TestApp : App() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .networkModule(StubNetworkModule())
                .databaseModule(StubDatabaseModule())
                .build()
    }
}
