package com.binas.yak.study.grammar

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import org.junit.Rule
import org.junit.Test

class ReviseGrammarWritingTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<GrammarReviseWritingActivity> =
        object : ActivityTestRule<GrammarReviseWritingActivity>(GrammarReviseWritingActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, GrammarReviseWritingActivity::class.java).apply {
                    putExtra("id", 4L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysAllActivitiesCorrectly() {
        onView(withId(R.id.sentence)).check(matches(withText("ང་མི་...")))
        onView(withId(R.id.writeItButton)).perform(click())
        intended(hasComponent(ReviseWritingActivity::class.java.name))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(CompareWritingActivity::class.java.name))
        onView(withId(R.id.sentenceTextView)).check(matches(withText("ང་མི་ཡིན།")))
    }
}