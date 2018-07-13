package click.toshi.ipaam1.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import click.toshi.ipaam1.di.ViewModelKey
import click.toshi.ipaam1.presentation.Main2Activity
import click.toshi.ipaam1.presentation.MainActivity
import click.toshi.ipaam1.presentation.issues.AllIssuesFragment
import click.toshi.ipaam1.presentation.issues.AllIssuesStore
import click.toshi.ipaam1.presentation.sessions.AllSessionsFragment
import click.toshi.ipaam1.presentation.sessions.AllSessionsStore

@Module interface Main2ActivityModule {
    @Binds fun providesAppCompatActivity(testActivity: Main2Activity): AppCompatActivity

    @ContributesAndroidInjector fun contributeIssuesFragment(): AllIssuesFragment

    @Binds @IntoMap
    @ViewModelKey(AllIssuesStore::class)
    fun bindAllIssuesStore(
            allIssuesStore: AllIssuesStore
    ): ViewModel
}
