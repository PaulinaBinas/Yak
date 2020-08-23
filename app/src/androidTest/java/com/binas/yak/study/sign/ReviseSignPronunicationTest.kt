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
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseSignPronunicationTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<SignReviseSoundActivity> =
        object : ActivityTestRule<SignReviseSoundActivity>(SignReviseSoundActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, SignReviseSoundActivity::class.java).apply {
                    putExtra("id", 1L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysTheResultCorrectly() {
        onView(withId(R.id.goNextButton)).perform(click())
        intended(hasComponent(PronunciationCheckActivity::class.java.name))
        onView(withId(R.id.textView)).check(matches(withText("ཀ (ka)")))
    }
}