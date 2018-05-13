package click.toshi.ipaam1.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import click.toshi.ipaam1.data.db.AppDatabase
import click.toshi.ipaam1.data.db.FavoriteDatabase
import click.toshi.ipaam1.data.db.FavoriteFirestoreDatabase
import click.toshi.ipaam1.data.db.SessionDatabase
import click.toshi.ipaam1.data.db.SessionRoomDatabase
import click.toshi.ipaam1.data.db.dao.SessionDao
import click.toshi.ipaam1.data.db.dao.SessionSpeakerJoinDao
import click.toshi.ipaam1.data.db.dao.SpeakerDao
import javax.inject.Singleton

@Module open class DatabaseModule {

    companion object {
        val instance = DatabaseModule()
    }

    @Singleton @Provides
    fun provideSessionDatabase(
            appDatabase: AppDatabase,
            sessionDbDao: SessionDao,
            speakerDao: SpeakerDao,
            sessionSpeakerJoinDao: SessionSpeakerJoinDao
    ): SessionDatabase =
            SessionRoomDatabase(appDatabase, sessionDbDao, speakerDao, sessionSpeakerJoinDao)

    @Singleton @Provides
    fun provideFavoriteDatabase(): FavoriteDatabase =
            FavoriteFirestoreDatabase()

    @Singleton @Provides
    open fun provideDb(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "ipaam1.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton @Provides
    fun provideSessionsDao(db: AppDatabase): SessionDao = db.sessionDao()

    @Singleton @Provides
    fun provideSpeakerDao(db: AppDatabase): SpeakerDao = db.speakerDao()

    @Singleton @Provides
    fun provideSessionSpeakerJoinDao(db: AppDatabase): SessionSpeakerJoinDao =
            db.sessionSpeakerDao()
}
