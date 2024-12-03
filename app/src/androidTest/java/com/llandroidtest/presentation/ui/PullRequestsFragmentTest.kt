package com.llandroidtest.presentation.ui

import android.content.Intent
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.llandroidtest.R
import com.presentation.ui.fragment.PullRequestsFragment
import com.llandroidtest.presentation.utils.MocksRepository

@RunWith(AndroidJUnit4::class)
class PullRequestsFragmentTest {

    @Test
    fun testPullRequestsAreDisplayed() {
        val mockPullRequests = MocksRepository.mockPullRequests

        val scenario = launchFragmentInContainer<com.presentation.ui.fragment.PullRequestsFragment>(
            themeResId = R.style.Theme_LLAndroidTest
        )

        scenario.onFragment { fragment ->
            fragment.adapter.submitList(mockPullRequests)
        }

        onView(withId(com.presentation.R.id.rv_pull_requests)).check(matches(isDisplayed()))

        onView(withId(com.presentation.R.id.rv_pull_requests))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        onView(withText("Test PR 1")).check(matches(isDisplayed()))
        onView(withText("Test PR 2")).check(matches(isDisplayed()))
    }

    @Test
    fun testPullRequestClickOpensBrowser() {
        val mockPullRequest = MocksRepository.mockPullRequest1

        val scenario = launchFragmentInContainer<com.presentation.ui.fragment.PullRequestsFragment>(
            themeResId = R.style.Theme_LLAndroidTest
        )

        scenario.onFragment { fragment ->
            fragment.adapter.submitList(listOf(mockPullRequest))
        }

        Intents.init()
        onView(withText("Test PR")).perform(click())
        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasData("https://github.com/example/pull/1"))
        Intents.release()
    }
}
