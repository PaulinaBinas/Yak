package com.binas.yak.achievements

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.R
import com.binas.yak.ui.achievements.view.AchievementsActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class AchievementsUITest {

    @get:Rule
    val activityRule = ActivityTestRule(AchievementsActivity::class.java)

    @Test
    fun displaysCorrectData() {
        onView(withId(R.id.signsCount)).check(matches(withSubstring("0/111")))
        onView(withId(R.id.wordsCount)).check(matches(withSubstring("1/500")))
        onView(withId(R.id.grammarCount)).check(matches(withSubstring("0/205")))
    }
}