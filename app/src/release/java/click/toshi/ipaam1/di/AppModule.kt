package click.toshi.ipaam1.di

import dagger.Module
import dagger.Provides
import click.toshi.ipaam1.AppLifecycleCallbacks
import click.toshi.ipaam1.data.di.DataModule
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule {

  @Singleton
  @Provides
  fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = ReleaseAppLifecycleCallbacks()

}
