package click.toshi.ipaam1.di

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import dagger.Module
import dagger.Provides
import click.toshi.ipaam1.data.api.ApplicationApi
import click.toshi.ipaam1.data.db.FavoriteDatabase
import click.toshi.ipaam1.data.db.SessionDatabase
import click.toshi.ipaam1.data.repository.SessionDataRepository
import click.toshi.ipaam1.data.repository.SessionRepository
import click.toshi.ipaam1.util.rx.AppSchedulerProvider
import click.toshi.ipaam1.util.rx.SchedulerProvider
import javax.inject.Singleton

@Module internal object AppModule {
    @Singleton @Provides @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton @Provides @JvmStatic
    fun provideSessionRepository(
            api: ApplicationApi,
            sessionDatabase: SessionDatabase,
            favoriteDatabase: FavoriteDatabase,
            schedulerProvider: SchedulerProvider
    ): SessionRepository =
            SessionDataRepository(api, sessionDatabase, favoriteDatabase,
                    schedulerProvider)

    @Singleton @Provides @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Singleton
    @Provides
    @JvmStatic
    fun provideNotificationManager(context: Context): NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}
