package com.llandroidtest.presentation.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test
import com.llandroidtest.R
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.presentation.ui.activity.MainActivity
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testToolbarNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(com.presentation.R.id.toolbar)).check(matches(isDisplayed()))

        onView(withId(com.presentation.R.id.toolbar)).perform(click())

        activityScenario.onActivity {
            val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)

            navController.setGraph(com.presentation.R.navigation.nav_graph)
            navController.setCurrentDestination(com.presentation.R.id.fragmentUserRepository)

            assert(navController.currentDestination?.id == com.presentation.R.id.fragmentUserRepository)
        }
    }
}
