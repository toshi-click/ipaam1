package click.toshi.ipaam1.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the implementation of [UserDao]
 */
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UsersDatabase

    @Before fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                UsersDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After fun closeDb() {
        database.close()
    }

    @Test fun getUsersWhenNoUserInserted() {
        database.userDao().getUserById("123")
                .test()
                .assertNoValues()
    }

    @Test fun insertAndGetUser() {
        // When inserting a new user in the data source
        database.userDao().insertUser(USER)

        // When subscribing to the emissions of the user
        database.userDao().getUserById(USER.id)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == USER.id && it.userName == USER.userName }
    }

    @Test fun updateAndGetUser() {
        // Given that we have a user in the data source
        database.userDao().insertUser(USER)

        // When we are updating the name of the user
        val updatedUser = User(USER.id, "new username")
        database.userDao().insertUser(updatedUser)

        // When subscribing to the emissions of the user
        database.userDao().getUserById(USER.id)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == USER.id && it.userName == "new username" }
    }

    @Test fun deleteAndGetUser() {
        // Given that we have a user in the data source
        database.userDao().insertUser(USER)

        //When we are deleting all users
        database.userDao().deleteAllUsers()
        // When subscribing to the emissions of the user
        database.userDao().getUserById(USER.id)
                .test()
                // check that there's no user emitted
                .assertNoValues()
    }

    companion object {
        private val USER = User("id", "username")
    }
}
