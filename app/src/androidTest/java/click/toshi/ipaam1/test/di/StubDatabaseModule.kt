package click.toshi.ipaam1.test.di

import android.app.Application
import android.arch.persistence.room.Room
import click.toshi.ipaam1.data.db.AppDatabase
import click.toshi.ipaam1.di.DatabaseModule

class StubDatabaseModule : DatabaseModule(){

    override fun provideDb(app: Application): AppDatabase =
            Room
                    .inMemoryDatabaseBuilder(app, AppDatabase::class.java)
                    .fallbackToDestructiveMigration().build()

}
