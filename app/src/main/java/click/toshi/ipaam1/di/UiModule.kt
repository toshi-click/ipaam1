package click.toshi.ipaam1.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import click.toshi.ipaam1.di.modules.MainDispatcherModule
import click.toshi.ipaam1.di.modules.MainModule
import click.toshi.ipaam1.main.MainActivity

@Module
internal abstract class UiModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): StoreProvider

  @PerActivityScope
  @ContributesAndroidInjector(modules = [MainModule::class, MainDispatcherModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

}
