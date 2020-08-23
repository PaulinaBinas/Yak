package com.binas.yak.study.sign

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
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardActivity
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class LearnSignTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<SignStudyCardActivity> =
        object : ActivityTestRule<SignStudyCardActivity>(SignStudyCardActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, SignStudyCardActivity::class.java).apply {
                    putExtra("id", 1L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    val description = "Imagine a person, who is not very flexible and has short arms. Then picture " +
            "them bending down to try and reach their feet, while saying “CAn’t!” in an annoyed " +
            "voice. That’s the Tibetan “KA” sign. Try drawing it in the order presented, all the " +
            "while keeping the image vivid in your mind."

    @Test
    fun displaysSignStudyActivity() {
        Thread.sleep(1_000)
        onView(withId(R.id.signText)).check(matches(withText("ཀ (ka)")))
        onView(withId(R.id.description)).check(matches(withText(description)))
    }

    @Test
    fun goesToPracticeWriting() {
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(LearnSignWritingActivity::class.java.name))
    }
}