package com.binas.yak.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ChangeNumberOfCardsTest: AbstractGuiTest() {

    @get:Rule
    var activityRule = ActivityTestRule(ChangeLimitActivity::class.java)

    @Test
    fun changesDailyLimitOfNewCards() {
        onView(withId(R.id.editTextNumber)).perform(replaceText("4"))
        onView(withId(R.id.saveLimitButton)).perform(click())
    }
}