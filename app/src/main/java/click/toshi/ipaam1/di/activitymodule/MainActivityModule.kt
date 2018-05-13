package click.toshi.ipaam1.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import click.toshi.ipaam1.di.ViewModelKey
import click.toshi.ipaam1.presentation.MainActivity
import click.toshi.ipaam1.presentation.sessions.AllSessionsFragment
import click.toshi.ipaam1.presentation.sessions.AllSessionsStore

@Module interface MainActivityModule {
    @Binds fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector fun contributeAllSessionsFragment(): AllSessionsFragment

    @Binds @IntoMap
    @ViewModelKey(AllSessionsStore::class)
    fun bindAllSessionsStore(
            allSessionsStore: AllSessionsStore
    ): ViewModel
}
