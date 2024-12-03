package com.llandroidtest.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.llandroidtest.R
import com.llandroidtest.presentation.ui.activity.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UserRepositoryFragmentTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testRecyclerViewIsVisibleAndScrollable() {
        onView(withId(R.id.nav_host_fragment)).perform(ViewActions.click())

        onView(withId(R.id.recyclerViewUserRepositories)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerViewUserRepositories)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )

        onView(withId(R.id.recyclerViewUserRepositories)).check(matches(hasMinimumChildCount(0)))
    }
}