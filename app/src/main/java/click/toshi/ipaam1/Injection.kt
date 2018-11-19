
package click.toshi.ipaam1

import android.content.Context
import click.toshi.ipaam1.persistence.UserDao
import click.toshi.ipaam1.persistence.UsersDatabase
import click.toshi.ipaam1.ui.ViewModelFactory

/**
 * Enables injection of data sources.
 */
object Injection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
