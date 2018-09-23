package click.toshi.ipaam1.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import click.toshi.ipaam1.di.PerActivityScope
import click.toshi.ipaam1.di.ViewModelKey
import click.toshi.ipaam1.main.MainDispatcher
import click.toshi.ipaam1.main.MainStore
import click.toshi.ipaam1.main.RepoDetailDialogFragment

@Module
internal abstract class MainModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainStore::class)
  abstract fun bindMainStore(viewModel: MainStore): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeRepoDetailDialogFragment(): RepoDetailDialogFragment
}

@Module
internal class MainDispatcherModule {

  @PerActivityScope
  @Provides
  fun provideMainDispatcher() = MainDispatcher()

}
