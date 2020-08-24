package com.binas.yak.study.vocabulary

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseVocabularyMeaningTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<VocabularyReviseMeaningActivity> =
        object : ActivityTestRule<VocabularyReviseMeaningActivity>(VocabularyReviseMeaningActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, VocabularyReviseMeaningActivity::class.java).apply {
                    putExtra("id", 4L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysAllActivitiesCorrectly() {
        onView(withId(R.id.vocabularyTextView)).check(matches(withText("ང་")))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(MeaningCheckActivity::class.java.name))
        onView(withId(R.id.translationTextView)).check(matches(withText("I")))
        onView(withId(R.id.tibetanWordTextView)).check(matches(withText("ང་")))
    }
}