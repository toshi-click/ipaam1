
package click.toshi.ipaam1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import click.toshi.ipaam1.persistence.UserDao

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
