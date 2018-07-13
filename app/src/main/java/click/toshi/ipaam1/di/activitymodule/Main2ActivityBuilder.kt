package click.toshi.ipaam1.di.activitymodule

import dagger.Module
import dagger.android.ContributesAndroidInjector
import click.toshi.ipaam1.di.RecycledViewPoolModule
import click.toshi.ipaam1.presentation.Main2Activity
import click.toshi.ipaam1.presentation.MainActivity

@Module interface Main2ActivityBuilder {
    @ContributesAndroidInjector(modules = [
        Main2ActivityModule::class,
        RecycledViewPoolModule::class
    ])
    fun contributeMain2Activity(): Main2Activity
}
