package com.binas.yak.study.sign

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseSignWritingTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<SignReviseWritingActivity> =
        object : ActivityTestRule<SignReviseWritingActivity>(SignReviseWritingActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, SignReviseWritingActivity::class.java).apply {
                    putExtra("id", 1L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysThreeActivities() {
        onView(withId(R.id.writeItButton)).perform(click())
        intended(hasComponent(ReviseWritingActivity::class.java.name))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(CompareWritingActivity::class.java.name))
    }
}