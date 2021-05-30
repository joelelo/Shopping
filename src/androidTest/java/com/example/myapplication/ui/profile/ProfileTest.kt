package com.example.myapplication.ui.profile

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.MAEAss.ui.profile.EditProfileFragment
import com.google.common.truth.Truth.assertThat
import com.example.mae.MainActivity
import com.example.mae.ui.home.ProfileFragment
import com.example.myapplication.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileTest{
    //TEST IF CLICKING THE EDIT BUTTON OPENS THE EDIT FRAGMENT
    @Test
    fun test_edit_profile() {
        var navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val scenario = launchFragmentInContainer<ProfileFragment>()

        scenario.onFragment{fragment->
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(fragment.requireView(),navController)
        }
        //Go to edit and click edit
        onView(withId(R.id.btnEdit)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.navigation_edit)

    }

    //TEST IF THE
    @Test
    fun test_edit_name(){
        var navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val scenario = launchFragmentInContainer<EditProfileFragment>()

        scenario.onFragment{fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.navigation_edit)
            Navigation.setViewNavController(fragment.requireView() ,navController)
        }
        val textToType = "Joel A"
        //CHANGE EDITTEXT
        onView(withId(R.id.etEditName)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.etEditName)).perform(typeText(textToType))
        closeSoftKeyboard()
        onView(withId(R.id.btnSave)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.navigation_profile)

    }
}