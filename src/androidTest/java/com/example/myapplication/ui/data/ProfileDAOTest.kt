package com.example.myapplication.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.MAEAss.ui.data.Profile
import com.example.MAEAss.ui.data.ProfileDAO
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.Data.ProductDAO
import com.example.myapplication.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProfileDAOTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ProfileDAO

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.profileDAO
    }

    @After
    fun teardown(){
        database.close()
    }

    //TEST PROFILE DAO
    @Test
    fun insert() = runBlockingTest{
        val profile = Profile(1,1,"name","address")
        dao.insert(profile)
        val getprofile = dao.getProfile().getOrAwaitValue()

        assertThat(getprofile).isEqualTo(profile)
    }

    @Test
    fun update() = runBlockingTest {
        val profile = Profile(1,1,"name","address")
        val profile2 = Profile(1,2,"name2","address2")
        dao.insert(profile)
        dao.update(profile2)
        val getprofile = dao.getProfile().getOrAwaitValue()

        assertThat(getprofile).isEqualTo(profile2)
    }
}