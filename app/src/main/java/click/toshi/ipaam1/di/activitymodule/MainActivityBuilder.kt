package click.toshi.ipaam1.di.activitymodule

import dagger.Module
import dagger.android.ContributesAndroidInjector
import click.toshi.ipaam1.di.RecycledViewPoolModule
import click.toshi.ipaam1.presentation.MainActivity

@Module interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        RecycledViewPoolModule::class
    ])
    fun contributeMainActivity(): MainActivity
}
